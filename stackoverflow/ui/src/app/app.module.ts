import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import {AppComponent } from './app.component';
import {HomeComponent } from './content/pages/home/home.component';
import {QuestionsComponent } from './content/pages/questions/questions.component';
import {QuestionService} from "./service/question/question.service";
import {HttpClientModule} from "@angular/common/http";
import {HeaderComponent} from './content/global/header/header.component';
import {TabMenuModule} from "primeng/tabmenu";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {QuestionComponent} from './content/pages/question/question.component';
import {AnswerService} from "./service/answer/answer.service";
import {UserService} from "./service/user/user.service";
import {TagService} from "./service/tag/tag.service";
import {RelationshipService} from "./service/relationship/relationship.service";
import {VoteService} from "./service/vote/vote.service";
import { UsersComponent } from './content/pages/users/users.component';
import { AnswerQuestionComponent } from './content/pages/answer-question/answer-question.component';
import { AskQuestionComponent } from './content/pages/ask-question/ask-question.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    QuestionsComponent,
    HeaderComponent,
    QuestionComponent,
    UsersComponent,
    AnswerQuestionComponent,
    AskQuestionComponent,

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    TabMenuModule
  ],
  providers: [
    QuestionService,
    AnswerService,
    UserService,
    TagService,
    RelationshipService,
    VoteService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
