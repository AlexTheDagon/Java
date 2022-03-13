package com.utcn.stackoverflow.repository;

import com.utcn.stackoverflow.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository <User, Integer> {

}
