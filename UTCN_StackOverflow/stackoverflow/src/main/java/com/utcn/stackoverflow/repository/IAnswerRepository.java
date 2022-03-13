package com.utcn.stackoverflow.repository;

import com.utcn.stackoverflow.model.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerRepository extends CrudRepository<Answer, Integer> {

}
