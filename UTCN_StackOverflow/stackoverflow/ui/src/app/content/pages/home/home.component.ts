import { Component, OnInit } from '@angular/core';
import {QuestionService} from "../../../service/question/question.service";
import {AnswerService} from "../../../service/answer/answer.service";
import {RelationshipService} from "../../../service/relationship/relationship.service";
import {TagService} from "../../../service/tag/tag.service";
import {VoteService} from "../../../service/vote/vote.service";
import {UserService} from "../../../service/user/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  questions: any[] = [];
  answers: any[] = [];
  users: any[] = [];
  votes: any[] = [];
  relationships: any[] = [];
  tags: any[] = [];

  constructor(
    private answerService: AnswerService,
    private questionsService: QuestionService,
    private relationshipService: RelationshipService,
    private tagService: TagService,
    private userService: UserService,
    private voteService: VoteService,
  ) { }

  ngOnInit(): void {
    this.retrieveTags();
    this.retrieveAnswers();
    this.retrieveUsers();
    this.retrieveQuestions();
    this.retrieveRelationships();
    this.retrieveVotes();
  }

  // region GET ALL QUESTIONS
  retrieveQuestions(): void {
    this.questionsService.getAll()
      .subscribe({
        next: (data) => {
          this.questions = data;
          console.log("QUESTIONS:");
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  // endregion

  // region GET ALL ANSWERS
  retrieveAnswers(): void {
    this.answerService.getAll()
      .subscribe({
        next: (data) => {
          this.answers = data;
          console.log("ANSWERS:");
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
  // endregion

  // region GET ALL VOTES
  retrieveVotes(): void {
    this.voteService.getAll()
      .subscribe({
        next: (data) => {
          this.votes = data;
          console.log("VOTES:");
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
  // endregion

  // region GET ALL USERS
  retrieveUsers(): void {
    this.userService.getAll()
      .subscribe({
        next: (data) => {
          this.users = data;
          console.log("USERS:");
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
  // endregion

  // region GET ALL TAGS
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
  // endregion

  // region GET ALL RELATIONSHIPS
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
  // endregion


}
