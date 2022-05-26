import { Component, OnInit } from '@angular/core';
import {AnswerService} from "../../../service/answer/answer.service";
import {ActivatedRoute} from "@angular/router";
import {QuestionService} from "../../../service/question/question.service";
import {TagService} from "../../../service/tag/tag.service";
import {RelationshipService} from "../../../service/relationship/relationship.service";
import {UserService} from "../../../service/user/user.service";
import {Answer} from "../../../model/answer/answer";
import {Question} from "../../../model/question/question";
import {Tag} from "primeng/tag";
import {Relationship} from "../../../model/relationship/relationship";

@Component({
  selector: 'app-ask-question',
  templateUrl: './ask-question.component.html',
  styleUrls: ['./ask-question.component.scss']
})
export class AskQuestionComponent implements OnInit {

  question: any;
  questions: any[] = [];
  relationships: any[] = [];
  tags: any[] = [];
  users: any[] = [];


  constructor(
    private answerService: AnswerService,
    private questionService: QuestionService,
    private tagService: TagService,
    private relationshipService: RelationshipService,
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
  ) { }




  ngOnInit(): void {
    this.retrieveQuestion(); // In case we edit
    this.retrieveQuestions();
    this.retrieveRelationships();
    this.retrieveTags();
    this.retrieveUsers();
  }

  retrieveQuestion(): void {
    let questionID = this.activatedRoute.snapshot.params['questionID'];
    //console.log(questionID);
    if(typeof questionID === 'undefined') return;
    this.questionService.get(+questionID)
      .subscribe({
        next: (data) => {
          this.question = data;
        },
        error: (e) => console.error(e)
      });
  }

  retrieveQuestions(): void {
    this.questionService.getAll()
      .subscribe({
        next: (data) => {
          this.questions = data;
          //console.log("QUESTIONS:");
          //console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  retrieveRelationships(): void {
    this.relationshipService.getAll()
      .subscribe({
        next: (data) => {
          this.relationships = data;
          //console.log("RELATIONSHIPS:");
          //console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  retrieveTags(): void {
    this.tagService.getAll()
      .subscribe({
        next: (data) => {
          this.tags = data;
          //console.log(this.tags);
          //console.log("TAGS:");
          //console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  retrieveUsers(): void {
    this.userService.getAll()
      .subscribe({
        next: (data) => {
          this.users = data;
          //console.log("USERS:");
          //console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  userPermission(): boolean {
    if(!localStorage["loggedUser"]) return false;
    return true;
  }

  loggedUser(): any {
    if(!localStorage["loggedUser"]) return null;
    let loggedUser = JSON.parse(localStorage.getItem("loggedUser") || "");
    return loggedUser;
  }

  tagsAsString(questionID: number): string {
    let tagsAsString = "";
    if(typeof questionID === 'undefined' || questionID === null) return tagsAsString;
    this.tags.forEach(t => {
      if(!(typeof this.relationships.find(r => (r.questionID == questionID && r.tagID == t.tagID)) === 'undefined'))
        tagsAsString += t.tagText + " ";
    });
    return tagsAsString;
  }

  deleteRelationships(questionID: number): void {
    this.relationships.forEach(r => {
      if(r.questionID == questionID)
        this.relationshipService.delete(r.relationshipID).subscribe({
          next: (data) => {
          },
          error: (e) => console.error(e)
        });
    })
  }

  createRelationships(newRelationships: any[]): void {
    this.relationships.forEach(r => {

        this.relationshipService.create(r).subscribe({
          next: (data) => {
          },
          error: (e) => console.error(e)
        });
    });
  }

  submitQuestion(): void {

    this.tags = this.tags.sort((a,b) => {
      if(a.tagID < b.tagID) return -1;
      if(a.tagID > b.tagID) return 1;
      return 0;
    })
    this.relationships = this.relationships.sort((a,b) => {
      if(a.relationshipID < b.relationshipID) return -1;
      if(a.relationshipID > b.relationshipID) return 1;
      return 0;
    })
    console.log(this.relationships);
    console.log(this.tags);
    let newQuestionCreate = true;
    let newUser = this.loggedUser();
    // @ts-ignore
    let newQuestionID = this.questions.at(- 1)?.questionID + 1;
    if(!(typeof this.question === 'undefined')) {
      newUser = this.users.find(u => (u.userID == this.question.userID));
      newQuestionID = this.question.questionID;
      newQuestionCreate = false;
    }
    let newUserID = newUser?.userID;
    let newQuestionDate = new Date().toISOString().slice(0, 19);
    // @ts-ignore
    let newQuestionTitle = document.getElementById("QuestionTitle").value;
    // @ts-ignore
    let newQuestionText = document.getElementById("QuestionText").value;
    // @ts-ignore
    let questionTagsString = document.getElementById("QuestionTags").value;
    let newQuestionTags: string[] = questionTagsString.trim().split(/\s+/);

    const newQuestion: Question = {
      questionID: newQuestionID,
      userID: newUserID,
      title: newQuestionTitle,
      questionText: newQuestionText,
      dateAndTime: newQuestionDate,
    };

    if(!newQuestionCreate)this.questionService.update(newQuestion).subscribe({
      next: (data) => {
      },
      error: (e) => console.error(e)
    });
    else this.questionService.create(newQuestion).subscribe({
      next: (data) => {
      },
      error: (e) => console.error(e)
    });
    //console.log(newQuestionTags);
    this.deleteRelationships(newQuestion.questionID);
    this.retrieveRelationships();
    newQuestionTags.forEach(t => {
        let tagID;
        let myTag = this.tags.find(tt => (tt.tagText == t));
        if((typeof myTag === 'undefined')) {
          // @ts-ignore
          tagID = this.tags.at(- 1)?.tagID + 1;

          const newTag: Tag = {
            // @ts-ignore
            tagID: tagID,
            tagText: t,
          }
          console.log(newTag);
          this.tagService.create(newTag).subscribe({
            next: (data) => {
            },
            error: (e) => console.error(e)
          });
          this.tags.push(newTag);
        } else {
          tagID = myTag.tagID;
        }
        // @ts-ignore
        let myRelationshipID = this.relationships.at(- 1)?.relationshipID + 1;
        const newRelationship: Relationship = {
          relationshipID: myRelationshipID,
          tagID: tagID,
          questionID: newQuestion.questionID,
        }
        console.log(newRelationship);
        this.relationshipService.create(newRelationship).subscribe({
          next: (data) => {
          },
          error: (e) => console.error(e)
        });
        this.relationships.push(newRelationship);
      }
    );
  }


}
