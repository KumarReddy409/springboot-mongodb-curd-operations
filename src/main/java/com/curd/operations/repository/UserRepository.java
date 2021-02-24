package com.curd.operations.repository;

import com.curd.operations.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User,String> {

    User findByEmail(String email);

    int deleteByEmail(String email);
}
