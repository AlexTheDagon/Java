import { Component, OnInit } from '@angular/core';
import {QuestionService} from "../../../service/question/question.service";
import {AnswerService} from "../../../service/answer/answer.service";
import {UserService} from "../../../service/user/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  users: any[] = [];

  constructor(
    private questionsService: QuestionService,
    private answersService: AnswerService,
    private userService: UserService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.retrieveUsers();
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

  computeType(type: number): string {
    var rez:string = "Normal";
    if(type == 1) rez = "Moderator";
    if(type == -1) rez = "Banned";
    return rez;
  }


}
