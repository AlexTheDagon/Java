package com.utcn.stackoverflow.repository;

import com.utcn.stackoverflow.model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoteRepository extends CrudRepository <Vote, Integer> {

}
