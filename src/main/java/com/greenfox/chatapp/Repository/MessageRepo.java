package com.greenfox.chatapp.Repository;

import com.greenfox.chatapp.Module.Message;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long>{

  List<Message> findAll();

}
