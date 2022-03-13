package com.utcn.stackoverflow.controller;

import com.utcn.stackoverflow.model.Question;
import com.utcn.stackoverflow.model.User;
import com.utcn.stackoverflow.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAllQuestions")
    @ResponseBody
    public List<Question> getAllQuestions() { return questionService.getAllQuestions(); }

    @RequestMapping(method = RequestMethod.GET, value = "/getQuestion")
    @ResponseBody
    public Question getQuestion(@RequestParam(name = "id") Integer id) {
        return questionService.getQuestion(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteQuestion")
    @ResponseBody
    public String deleteQuestion(@RequestParam(name = "id") Integer id) {
        return questionService.deleteQuestion(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createQuestion")
    @ResponseBody
    public Question createQuestion(@RequestBody Question question) { return questionService.saveQuestion(question); }

    @RequestMapping(method = RequestMethod.POST, value = "/updateQuestion")
    @ResponseBody
    public Question updateQuestion( @RequestBody Question question) { return questionService.updateQuestion(question.getQuestionID(), question);}

}
