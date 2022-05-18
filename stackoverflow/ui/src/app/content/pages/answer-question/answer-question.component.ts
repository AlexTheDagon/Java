import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from "@angular/router";
import {AnswerService} from "../../../service/answer/answer.service";
import {QuestionService} from "../../../service/question/question.service";
import {Answer} from "../../../model/answer/answer";


@Component({
  selector: 'app-answer-question',
  templateUrl: './answer-question.component.html',
  styleUrls: ['./answer-question.component.scss']
})
export class AnswerQuestionComponent implements OnInit {

  answer: any = [];
  question: any = [];
  answers: any[] = [];
  questionID: any;
  answerID: any;

  constructor(
    private answerService: AnswerService,
    private activatedRoute: ActivatedRoute,
    private questionsService: QuestionService,
  ) { }

  ngOnInit(): void {
    this.retrieveAnswer();
    this.retrieveAnswers();
    this.retrieveQuestion();
  }

  retrieveAnswer(): void {
    this.answerID = this.activatedRoute.snapshot.params['answerID'];
    if(typeof this.answerID === 'undefined') return;
    this.answerService.get(+this.answerID)
      .subscribe({
        next: (data) => {
          this.answer = data;
          console.log("ANSWER:");
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  retrieveAnswers(): void {
    this.answerService.getAll()
      .subscribe({
        next: (data) => {
          this.answers = data;
          //console.log(this.answers);
        },
        error: (e) => console.error(e)
      });
  }

  retrieveQuestion(): void {
    this.questionID = this.activatedRoute.snapshot.params['questionID'];
    if(typeof this.questionID === 'undefined') return;
    this.questionsService.get(+this.questionID)
      .subscribe({
        next: (data) => {
          this.question = data;
        },
        error: (e) => console.error(e)
      });
  }

  ownerPermission(userID: number): boolean {
    if(typeof userID == 'undefined') return true;
    if(!localStorage["loggedUser"]) return false;
    let myUser = JSON.parse(localStorage.getItem("loggedUser") || "");
    if(userID == myUser.userID) return true;
    return false;
  }

  submitAnswer(): void {

    let myUser = JSON.parse(localStorage.getItem("loggedUser") || "");
    let newDate = new Date().toISOString().slice(0, 19);
    // @ts-ignore
    let myAnswer = document.getElementById("answerTextArea").value;
    var myAnswerId: any;
    var createAnswer: boolean = false;
    if(typeof this.answerID === 'undefined') {
      // @ts-ignore
      myAnswerId = this.answers.at(- 1)?.answerID + 1;
    } else {
      // @ts-ignore
      myAnswerId = +this.answerID;
      createAnswer = true;
    }

    const newAnswer: Answer = {
      answerID: myAnswerId,
      questionID: +this.activatedRoute.snapshot.params['questionID'],
      userID: myUser.userID,
      answerText: myAnswer,
      dateAndTime: newDate
    };
    console.log("Answers");
    console.log(newAnswer);
    console.log(this.answers);
    if(!createAnswer)this.answerService.create(newAnswer).subscribe({
      next: (data) => {
      },
      error: (e) => console.error(e)
    });
    else this.answerService.update(newAnswer).subscribe({
      next: (data) => {
      },
      error: (e) => console.error(e)
    });;
  }



}
