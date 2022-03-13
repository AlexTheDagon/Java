package com.utcn.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    private Integer voteID;

    @Column(name = "userID")
    private Integer userID;

    @Column(name = "questionID")
    private Integer questionID;

    @Column(name = "answerID")
    private Integer answerID;

    @Column(name = "value")
    private Integer value;

    @ManyToOne
    @JoinColumn(name="userID", insertable = false,updatable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name="questionID", insertable = false,updatable = false)
    @JsonIgnore
    private Question question;

    @ManyToOne
    @JoinColumn(name="answerID", insertable = false,updatable = false)
    @JsonIgnore
    private Answer answer;

    public Vote() {
    }

    public Vote(Integer voteID, Integer userID, Integer questionID, Integer answerID, Integer value, User user, Question question, Answer answer) {
        this.voteID = voteID;
        this.userID = userID;
        this.questionID = questionID;
        this.answerID = answerID;
        this.value = value;
        this.user = user;
        this.question = question;
        this.answer = answer;
    }

    public Integer getVoteID() {
        return voteID;
    }

    public void setVoteID(Integer voteID) {
        this.voteID = voteID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public Integer getAnswerID() {
        return answerID;
    }

    public void setAnswerID(Integer answerID) {
        this.answerID = answerID;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
