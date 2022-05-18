package com.utcn.stackoverflow.repository;

import com.utcn.stackoverflow.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends CrudRepository<Question, Integer> {

}

