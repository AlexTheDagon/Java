import { Component, OnInit } from '@angular/core';
import {QuestionService} from "../../../service/question/question.service";
import {AnswerService} from "../../../service/answer/answer.service";
import {UserService} from "../../../service/user/user.service";
import {ActivatedRoute} from "@angular/router";
import {User} from "../../../model/user/user";

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

  modPermission():boolean {
    if(!localStorage["loggedUser"]) return false;
    let myUser = JSON.parse(localStorage.getItem("loggedUser") || "");
    if(myUser.type == 1) return true;
    return false;
  }

  banUser(userID: number): void {
    this.userService.get(userID).subscribe({
      next: (data) => {
        let myUser = data;
        const newUser : User = {
          userID: myUser.userID,
          username: myUser.username,
          password: myUser.password,
          logged: myUser.logged,
          type: -1,
        }
        console.log(newUser);
        this.userService.update(newUser)
          .subscribe({
            next: () => {
              location.reload();
            },
            error: (e) => console.error(e)
          });
      },
      error: (e) => console.error(e)
    });
  }
}
