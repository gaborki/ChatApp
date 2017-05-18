package com.greenfox.chatapp.Repository;

import com.greenfox.chatapp.Module.MessageToSend;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<MessageToSend, Long>{

}
