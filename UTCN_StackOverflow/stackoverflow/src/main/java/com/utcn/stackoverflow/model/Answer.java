package com.utcn.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Answer {

    @Id
    private Integer answerID;

    @Column(name = "questionID")
    private Integer questionID;

    @Column(name = "userID")
    private Integer userID;

    @Column(name = "answer_text")
    private String answerText;

    @Column(name = "dateandtime")
    private LocalDateTime dateAndTime;

    @ManyToOne
    @JoinColumn(name="userID", insertable = false,updatable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name="questionID", insertable = false,updatable = false)
    @JsonIgnore
    private Question question;

    @OneToMany(mappedBy = "answer")
    @JsonIgnore
    private List<Vote> votes;

    public Answer() {
    }

    public Answer(Integer answerID, Integer questionID, Integer userID, String answerText, LocalDateTime dateAndTime, User user, Question question, List<Vote> votes) {
        this.answerID = answerID;
        this.questionID = questionID;
        this.userID = userID;
        this.answerText = answerText;
        this.dateAndTime = dateAndTime;
        this.user = user;
        this.question = question;
        this.votes = votes;
    }

    public Integer getAnswerID() {
        return answerID;
    }

    public void setAnswerID(Integer answerID) {
        this.answerID = answerID;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
