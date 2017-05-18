package com.greenfox.chatapp.Repository;

import com.greenfox.chatapp.Module.MessageToSend;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<MessageToSend, Long>{

  List<MessageToSend> findAll();

}
