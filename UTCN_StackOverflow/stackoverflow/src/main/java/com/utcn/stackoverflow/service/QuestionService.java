package com.utcn.stackoverflow.service;

import com.utcn.stackoverflow.model.Question;
import com.utcn.stackoverflow.repository.IQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class QuestionService {
    @Autowired
    IQuestionRepository iQuestionRepository;

    public List<Question> getAllQuestions() { return (List<Question>) iQuestionRepository.findAll(); }

    public Question getQuestion(Integer id) {
        Optional<Question> question = iQuestionRepository.findById(id);
        return question.orElse(null);
    }

    public String deleteQuestion(Integer id) {
        try {
            iQuestionRepository.delete(this.getQuestion(id));

            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }

    public Question saveQuestion(Question question){
        return iQuestionRepository.save(question);
    }

    public Question updateQuestion(Integer id, Question question){
        Question initialQuestion = this.getQuestion(id);

        initialQuestion.setUserID(question.getUserID());
        initialQuestion.setTitle(question.getTitle());
        initialQuestion.setQuestionText(question.getQuestionText());
        initialQuestion.setDateAndTime(question.getDateAndTime());
        initialQuestion.setUser(question.getUser());
        initialQuestion.setAnswers(question.getAnswers());

        return iQuestionRepository.save(initialQuestion);
    }
}
