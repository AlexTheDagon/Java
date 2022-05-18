package com.utcn.stackoverflow.repository;


import com.utcn.stackoverflow.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagRepository extends CrudRepository <Tag, Integer> {

}
