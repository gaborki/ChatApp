package com.greenfox.chatapp.Repository;


import com.greenfox.chatapp.Module.AppUser;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<AppUser, Long> {

  // Select * From AppUser Where username = name
  List<AppUser> findByUsername(String name);

}
