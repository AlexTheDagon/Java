package com.utcn.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.InterruptedIOException;

@Entity
public class Relationship {
    @Id
    private Integer relationshipID;

    @Column(name = "tagID")
    private Integer tagID;

    @Column(name = "questionID")
    private Integer questionID;

    @ManyToOne
    @JoinColumn(name="tagID", insertable = false,updatable = false)
    @JsonIgnore
    private Tag tag;

    @ManyToOne
    @JoinColumn(name="questionID", insertable = false,updatable = false)
    @JsonIgnore
    private Question question;

    public Relationship(Integer relationshipID, Integer tagID, Integer questionID, Tag tag, Question question) {
        this.relationshipID = relationshipID;
        this.tagID = tagID;
        this.questionID = questionID;
        this.tag = tag;
        this.question = question;
    }

    public Relationship() {
    }

    public Integer getRelationshipID() {
        return relationshipID;
    }

    public void setRelationshipID(Integer relationshipID) {
        this.relationshipID = relationshipID;
    }

    public Integer getTagID() {
        return tagID;
    }

    public void setTagID(Integer tagID) {
        this.tagID = tagID;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
