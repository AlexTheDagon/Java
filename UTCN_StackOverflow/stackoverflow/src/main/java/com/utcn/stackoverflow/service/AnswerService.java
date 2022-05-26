package com.utcn.stackoverflow.service;

import com.utcn.stackoverflow.model.Answer;
import com.utcn.stackoverflow.repository.IAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    IAnswerRepository iAnswerRepository;

    public List<Answer> getAllAnswers() { return (List<Answer>) iAnswerRepository.findAll(); }

    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = iAnswerRepository.findById(id);
        return answer.orElse(null);
    }

    public String deleteAnswer(Integer id) {
        try {
            iAnswerRepository.delete(this.getAnswer(id));

            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }


    public Answer saveAnswer(Answer answer){
        return iAnswerRepository.save(answer);
    }

    public Answer updateAnswer(Integer id, Answer answer){
        Answer initialAnswer = this.getAnswer(id);
        initialAnswer.setQuestionID(answer.getQuestionID());
        initialAnswer.setUserID(answer.getUserID());
        initialAnswer.setAnswerText(answer.getAnswerText());
        initialAnswer.setDateAndTime(answer.getDateAndTime());
        //initialAnswer.setUser(answer.getUser());
        //initialAnswer.setQuestion(answer.getQuestion());

        return iAnswerRepository.save(initialAnswer);
    }

}
