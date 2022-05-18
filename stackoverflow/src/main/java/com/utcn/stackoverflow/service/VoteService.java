package com.utcn.stackoverflow.service;


import com.utcn.stackoverflow.model.Vote;
import com.utcn.stackoverflow.repository.IVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    IVoteRepository iVoteRepository;

    public List<Vote> getAllVotes() { return (List<Vote>) iVoteRepository.findAll(); }

    public Vote getVote(Integer id) {
        Optional<Vote> vote = iVoteRepository.findById(id);
        return vote.orElse(null);
    }

    public String deleteVote(Integer id) {
        try {
            iVoteRepository.delete(this.getVote(id));

            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }


    public Vote saveVote(Vote vote){
        return iVoteRepository.save(vote);
    }

    public Vote updateVote(Integer id, Vote vote){
        Vote initialVote = this.getVote(id);

        initialVote.setQuestionID(vote.getQuestionID());
        initialVote.setUserID(vote.getUserID());
        initialVote.setAnswerID(vote.getAnswerID());
        initialVote.setValue(vote.getValue());

        return iVoteRepository.save(initialVote);
    }

}
