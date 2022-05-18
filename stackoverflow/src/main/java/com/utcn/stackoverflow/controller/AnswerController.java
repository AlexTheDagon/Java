package com.utcn.stackoverflow.controller;


import com.utcn.stackoverflow.model.Answer;
import com.utcn.stackoverflow.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAllAnswers")
    @ResponseBody
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAnswer")
    @ResponseBody
    public Answer getAnswer(@RequestParam(name = "id") Integer id) {
        return answerService.getAnswer(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteAnswer")
    @ResponseBody
    public String deleteAnswer(@RequestParam(name = "id") Integer id) {
        return answerService.deleteAnswer(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createAnswer")
    @ResponseBody
    public Answer createAnswer(@RequestBody Answer answer) { return answerService.saveAnswer(answer); }

    @RequestMapping(method = RequestMethod.POST, value = "/updateAnswer")
    @ResponseBody
    public Answer updateAnswer( @RequestBody Answer answer) { return answerService.updateAnswer(answer.getAnswerID(), answer);}
}
