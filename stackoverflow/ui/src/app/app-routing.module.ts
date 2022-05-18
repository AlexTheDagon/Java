import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./content/pages/home/home.component";
import {QuestionsComponent} from "./content/pages/questions/questions.component";
import {QuestionComponent} from "./content/pages/question/question.component";
import {UsersComponent} from "./content/pages/users/users.component";
import {AnswerQuestionComponent} from "./content/pages/answer-question/answer-question.component";


const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: 'questions', component: QuestionsComponent },
  { path: 'questions/:id', component: QuestionComponent },
  { path: 'users', component: UsersComponent },
  { path: 'answerQuestion/:questionID', component: AnswerQuestionComponent },
  { path: 'answerQuestion/:questionID/:answerID', component: AnswerQuestionComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
