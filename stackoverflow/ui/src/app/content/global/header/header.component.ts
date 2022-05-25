import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../service/user/user.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  users: any[] = [];
  show: boolean = true;
  showAllert: boolean = false;
  constructor(
    private userService: UserService,
  ) { }

  ngOnInit() {
    this.retrieveUsers();

  }

  // region GET ALL USERS
  retrieveUsers(): void {
    this.userService.getAll()
      .subscribe({
        next: (data) => {
          this.users = data;
          if(!localStorage["clearedUserLoggings"]) {
            this.logOutAllUsers();
            localStorage["clearedUserLoggings"] = true;
          }
        },
        error: (e) => console.error(e)
      });
  }
  // endregion

  loggedInUser(): string {
    if(localStorage["loggedUser"]) return localStorage["loggedUser"].username;
    return "Login";
  }

  handleAlert(): void {
    this.showAllert = false;
  }

  logOutUser() {
    if(!localStorage["loggedUser"]) return;
    localStorage.removeItem("loggedUser");
  }

  logInUser() {
    // @ts-ignore
    let username = document.getElementById("userUsername").value;
    // @ts-ignore
    let password = document.getElementById("userPassword").value;
    let myUser = this.users.find(u => (u.username == username && u.password == password));

    if(typeof myUser === 'undefined') return;console.log(myUser);
    if(myUser.type == -1) {
      this.showAllert = true;
      return;
    }
    localStorage["loggedUser"] = JSON.stringify(myUser);
  }



  handleLoginMessage(): string {
    if(!localStorage["loggedUser"]) return "";
    let myUser = JSON.parse(localStorage.getItem("loggedUser") || "");
    return myUser?.username;
  }

  handleShow() :boolean {
    if(!localStorage["loggedUser"]) return true;
    return false;
  }

  logOutUserById(userID: number): void {
    let myUser = this.users.find(u => u.userID == userID);
    myUser.logged = false;
    if(typeof myUser.userID === 'undefined') return;
    this.userService.update(myUser).subscribe({
      next: (data) => {
      },
      error: (e) => console.error(e)
    });
  }

  logOutAllUsers() {
    let loggedInUsers = this.users.filter(u => u.logged === true);
    loggedInUsers.forEach( u => this.logOutUserById(u.userID));
  }

}
