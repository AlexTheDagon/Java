import { Component, OnInit } from '@angular/core';
import {QuestionService} from "../../../service/question/question.service";
import {AnswerService} from "../../../service/answer/answer.service";
import {UserService} from "../../../service/user/user.service";
import {ActivatedRoute} from "@angular/router";
import {TagService} from "../../../service/tag/tag.service";
import {RelationshipService} from "../../../service/relationship/relationship.service";
import {VoteService} from "../../../service/vote/vote.service";


@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.scss']
})
export class QuestionsComponent implements OnInit {

  questions: any[] = [];
  answers: any[] = [];
  users: any[] = [];
  tags:any[] = [];
  relationships:any[] = [];
  filterType: number = 0;
  filterText: string = "";


  constructor(
    private answerService: AnswerService,
    private questionsService: QuestionService,
    private relationshipService: RelationshipService,
    private tagService: TagService,
    private userService: UserService,
    private voteService: VoteService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(){
    this.retrieveTags();
    this.retrieveAnswers();
    this.retrieveUsers();
    this.retrieveQuestions();
    this.retrieveRelationships();
  }

  cmp(q1: string, q2:string): number {
    return q1.localeCompare(q2);
  }

  retrieveQuestions(): void {
    this.questionsService.getAll()
      .subscribe({
        next: (data) => {

          this.questions = data.sort((q1,q2) => this.cmp(q1.dateAndTime, q2.dateAndTime));

        },
        error: (e) => console.error(e)
      });
  }

  retrieveAnswers(): void {
    this.answerService.getAll()
      .subscribe({
        next: (data) => {
          this.answers = data.filter(ans => (ans.questionID == (+this.activatedRoute.snapshot.params['id'])));
          //console.log(this.answers);
        },
        error: (e) => console.error(e)
      });
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
    if(myUsers.length > 0) username = myUsers[0].username;
    return username;

  }

  retrieveRelationships(): void {
    this.relationshipService.getAll()
      .subscribe({
        next: (data) => {
          this.relationships = data;
          console.log("RELATIONSHIPS:");
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  retrieveTags(): void {
    this.tagService.getAll()
      .subscribe({
        next: (data) => {
          this.tags = data;
          console.log("TAGS:");
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  questionTags(questionID:number): any[] {
    let questionRelationships = this.relationships.filter(rel => (rel.questionID == questionID));
    let questionTags = this.tags.filter(tag => questionRelationships.find(rel => (rel.tagID == tag.tagID)));
    return questionTags;
  }

  filteredQuestions(filterType:number, filterText:string): any[] {
    var filteredQuestions: any[] = this.questions;
    if (filterText == "") filterType = 0;
    if (filterType == 1) filteredQuestions = this.questions.filter(q => q.title.includes(filterText));
    if (filterType == 2) filteredQuestions = this.questions.filter(q => this.questionTags(q.questionID).some(t => (t.tagText == filterText)));
    return filteredQuestions;
  }

  modifyFilter(filterType: number) : void {
    this.filterType = filterType;
    // @ts-ignore
    this.filterText = document.getElementById("searchFilterInput").value;
  }

}
