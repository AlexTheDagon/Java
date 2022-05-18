package com.utcn.stackoverflow.repository;

import com.utcn.stackoverflow.model.Relationship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRelationshipRepository extends CrudRepository <Relationship, Integer> {

}
