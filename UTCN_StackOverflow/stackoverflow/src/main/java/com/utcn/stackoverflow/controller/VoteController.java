package com.utcn.stackoverflow.controller;


import com.utcn.stackoverflow.model.Answer;
import com.utcn.stackoverflow.model.Vote;
import com.utcn.stackoverflow.service.AnswerService;
import com.utcn.stackoverflow.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/vote")
public class VoteController {
    @Autowired
    VoteService voteService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAllVotes")
    @ResponseBody
    public List<Vote> getAllVotes() {
        return voteService.getAllVotes();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getVote")
    @ResponseBody
    public Vote getVote(@RequestParam(name = "id") Integer id) {
        return voteService.getVote(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteVote")
    @ResponseBody
    public String deleteVote(@RequestParam(name = "id") Integer id) {
        return voteService.deleteVote(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createVote")
    @ResponseBody
    public Vote createVote(@RequestBody Vote vote) { return voteService.saveVote(vote); }

    @RequestMapping(method = RequestMethod.POST, value = "/updateVote")
    @ResponseBody
    public Vote updateVote( @RequestBody Vote vote) { return voteService.updateVote(vote.getVoteID(), vote);}
}
