import { Component, OnInit } from '@angular/core';
import { QuestionService } from "../../../service/question/question.service";
import { ActivatedRoute} from "@angular/router";
import {AnswerService} from "../../../service/answer/answer.service";
import {UserService} from "../../../service/user/user.service";
import {VoteService} from "../../../service/vote/vote.service";
import {Vote} from "../../../model/vote/vote";


@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})


export class QuestionComponent implements OnInit {
  question: any;
  answers: any[] = [];
  allAnswers: any[] = [];
  users: any[] = [];
  votes: any[] = [];
  questions: any[] = [];

  constructor(
    private questionsService: QuestionService,
    private answersService: AnswerService,
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private voteService: VoteService,
    ) { }

  ngOnInit(): void {
    this.retrieveQuestion();
    this.retrieveAnswers();
    this.retrieveAllAnswers();
    this.retrieveUsers();
    this.retrieveVotes();
    this.retrieveQuestions();
  }

  retrieveAllAnswers(): void {
    this.answersService.getAll()
      .subscribe({
        next: (data) => {
          this.allAnswers = data;
          //console.log(this.answers);
        },
        error: (e) => console.error(e)
      });
  }


  retrieveVotes(): void {
    this.voteService.getAll()
      .subscribe({
        next: (data) => {
          this.votes = data;
          //console.log("VOTES:");
          //console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  retrieveQuestion(): void {
    this.questionsService.get(+this.activatedRoute.snapshot.params['id'])
      .subscribe({
        next: (data) => {
          this.question = data;
          //console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  retrieveQuestions(): void {
    this.questionsService.getAll()
      .subscribe({
        next: (data) => {
          this.questions = data;
          //console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  sortCmp(a: number, b: number) : number {
    if(a < b) return -1;
    if(a > b) return 1;
    return 0;
  }

  retrieveAnswers(): void {
    this.answersService.getAll()
      .subscribe({
        next: (data) => {
          this.answers = data.filter(ans => (ans.questionID == (+this.activatedRoute.snapshot.params['id'])))
          //console.log(this.answers);
        },
        error: (e) => console.error(e)
      });
  }

  sortedAnswers() : any[] {
    let srtAns = this.answers.sort().sort((a,b) => this.sortCmp(this.answerVoteCount(b.answerID) , this.answerVoteCount(a.answerID)));
    return srtAns;
  }

  retrieveUsers(): void {
    this.userService.getAll()
      .subscribe({
        next: (data) => {
          this.users = data;
          //console.log(this.answers);
        },
        error: (e) => console.error(e)
      });
  }

  retriveUserById(userID: number): string {
    var username:string = "";
    let myUsers = this.users.filter(user => user.userID == userID);
    if (myUsers.length > 0) username = myUsers[0].username;
    return username;

  }

  ownerPermission(userID: number): boolean {
    if(!localStorage["loggedUser"]) return false;
    let myUser = JSON.parse(localStorage.getItem("loggedUser") || "");
    if(myUser.type == 1 || userID == myUser.userID) return true;
    return false;
  }

  userPermission(userID: number): boolean {
    if(!localStorage["loggedUser"]) return false;
    return true;
  }

  deleteAnswer(deleteAnswerID: number) : void {
    this.answersService.delete(deleteAnswerID).subscribe({
      next: (data) => {
      },
      error: (e) => console.error(e)
    });
    location.reload();
  }

  deleteQuestion(deleteQuestionID: number) : void {
    this.questionsService.delete(deleteQuestionID).subscribe({
      next: (data) => {
        location.reload();
      },
      error: (e) => console.error(e)
    });

  }

  votePermission(userID: number): boolean {
    return this.userPermission(userID) && !this.ownerPermission(userID);
  }

  answerVoteCount(answerID:number) : number {
    var score: number = 0;
    score = this.votes.filter(v => (v.answerID == answerID && v.value == 1)).length - this.votes.filter(v => (v.answerID == answerID && v.value == -1)).length;
    return score;
  }

  questionVoteCount(questionID:number) : number {
    var score: number = 0;
    score = this.votes.filter(v => (v.questionID == questionID && v.value == 1)).length - this.votes.filter(v => (v.questionID == questionID && v.value == -1)).length;
    return score;
  }

  getUserID(): number {
    let myUser = JSON.parse(localStorage.getItem("loggedUser") || "");
    return myUser.userID;
  }

  voteAnswer(userID: number, answerID: number, value: number) : void {
    var myVoteID: number = 1;
    if(this.votes.length > 0)  {
      // @ts-ignore
      myVoteID = this.votes.at(-1).voteID + 1;
    }

    let existingVote = this.votes.find(v => v?.userID == userID && v?.answerID == answerID);
    if(!(typeof existingVote === 'undefined')) myVoteID = existingVote.voteID;
    const newVote: Vote = {
      voteID: myVoteID,
      answerID: answerID,
      questionID: null,
      userID: userID,
      value: value
    };
    if(typeof existingVote === 'undefined') {
      this.voteService.create(newVote).subscribe({next: (data) => {}, error: (e) => console.error(e)});
    } else {
      if(existingVote.value != value) {
        this.voteService.update(newVote).subscribe({next: (data) => {}, error: (e) => console.error(e)});
      } else {
        this.voteService.delete(newVote.voteID).subscribe({next: (data) => {}, error: (e) => console.error(e)});
      }
    }
    location.reload();
  }

  voteQuestion(userID: number, questionID: number, value: number) : void {
    var myVoteID: number = 1;
    if(this.votes.length > 0)  {
      // @ts-ignore
      myVoteID = this.votes.at(-1).voteID + 1;
    }

    let existingVote = this.votes.find(v => v?.userID == userID && v?.questionID == questionID);
    if(!(typeof existingVote === 'undefined')) myVoteID = existingVote.voteID;
    const newVote: Vote = {
      voteID: myVoteID,
      answerID: null,
      questionID: questionID,
      userID: userID,
      value: value
    };
    if(typeof existingVote === 'undefined') {
      this.voteService.create(newVote).subscribe({next: (data) => {}, error: (e) => console.error(e)});
    } else {
      if(existingVote.value != value) {
        this.voteService.update(newVote).subscribe({next: (data) => {}, error: (e) => console.error(e)});
      } else {
        this.voteService.delete(newVote.voteID).subscribe({next: (data) => {}, error: (e) => console.error(e)});
      }
    }
    location.reload();
  }

  computeUserScore(userID: number):number {
    let totalScore:number = 0;
    let userQuestions = this.questions.filter(q => q.userID == userID);
    let userAnswers = this.allAnswers.filter(a => a.userID == userID);
    let userVotes = this.votes.filter(v => (v.userID == userID && v.value == -1));
    this.votes.forEach(v => {
      if(v.answerID != null && !(typeof v.answerID === 'undefined')) {
        if(!(typeof userAnswers.find(a => (a.answerID == v.answerID)) === 'undefined')) {
          if(v.value > 0) totalScore += 10;
          else totalScore -= 2;
        }
      } else {
        if(!(typeof userQuestions.find(q => (q.questionID == v.questionID)) === 'undefined')) {
          if(v.value > 0) totalScore += 5;
          else totalScore -= 2;
        }
      }
    })
    totalScore -= userVotes.length;
    return totalScore;
  }

}
