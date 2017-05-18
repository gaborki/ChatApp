package com.greenfox.chatapp.Repository;


import com.greenfox.chatapp.Module.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

}
