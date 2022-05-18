package com.utcn.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tag {

    @Id
    private Integer tagID;

    @Column(name = "tag_text")
    private String tagText;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Relationship> relationships;

    public Tag(Integer tagID, String tagText, List<Relationship> relationships) {
        this.tagID = tagID;
        this.tagText = tagText;
        this.relationships = relationships;
    }

    public Tag() {
    }

    public Integer getTagID() {
        return tagID;
    }

    public void setTagID(Integer tagID) {
        this.tagID = tagID;
    }

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }
}
