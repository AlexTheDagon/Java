package com.utcn.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Question {
    @Id
    private Integer questionID;

    @Column(name = "userID")
    private Integer userID;

    @Column(name = "title")
    private String title;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "dateandtime")
    private LocalDateTime dateAndTime;

    @ManyToOne
    @JoinColumn(name="userID", insertable = false,updatable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Answer> answers;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vote> votes;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Relationship> relationships;

    public Question() {
    }

    public Question(Integer questionID, Integer userID, String title, String questionText, LocalDateTime dateAndTime, User user, List<Answer> answers, List<Vote> votes, List<Relationship> relationships) {
        this.questionID = questionID;
        this.userID = userID;
        this.title = title;
        this.questionText = questionText;
        this.dateAndTime = dateAndTime;
        this.user = user;
        this.answers = answers;
        this.votes = votes;
        this.relationships = relationships;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }
}
