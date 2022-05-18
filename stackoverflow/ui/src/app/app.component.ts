import { Component } from '@angular/core';
import {AnswerService} from "./service/answer/answer.service";
import {QuestionService} from "./service/question/question.service";
import {RelationshipService} from "./service/relationship/relationship.service";
import {TagService} from "./service/tag/tag.service";
import {UserService} from "./service/user/user.service";
import {VoteService} from "./service/vote/vote.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'ui';

}
