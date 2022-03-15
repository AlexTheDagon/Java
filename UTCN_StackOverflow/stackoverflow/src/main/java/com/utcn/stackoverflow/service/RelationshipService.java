package com.utcn.stackoverflow.service;

import com.utcn.stackoverflow.model.Relationship;
import com.utcn.stackoverflow.repository.IRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RelationshipService {
    @Autowired
    IRelationshipRepository iRelationshipRepository;

    public List<Relationship> getAllRelationships() { return (List<Relationship>) iRelationshipRepository.findAll(); }

    public Relationship getRelationship(Integer id) {
        Optional<Relationship> relationship = iRelationshipRepository.findById(id);
        return relationship.orElse(null);
    }

    public String deleteRelationship(Integer id) {
        try {
            iRelationshipRepository.delete(this.getRelationship(id));

            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }

    public Relationship saveRelationship(Relationship relationship){
        return iRelationshipRepository.save(relationship);
    }

    public Relationship updateRelationship(Integer id, Relationship relationship){
        Relationship initialRelationship = this.getRelationship(id);

        initialRelationship.setQuestionID(relationship.getQuestionID());
        initialRelationship.setTagID(relationship.getTagID());
        //initialRelationship.setQuestion(relationship.getQuestion());
        //initialRelationship.setTag(relationship.getTag());

        return iRelationshipRepository.save(initialRelationship);
    }
}
