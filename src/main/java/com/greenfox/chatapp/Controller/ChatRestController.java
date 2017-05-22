package com.greenfox.chatapp.Controller;


import com.greenfox.chatapp.Module.ErrorStatus;
import com.greenfox.chatapp.Module.Message;
import com.greenfox.chatapp.Module.IncomingMessage;
import com.greenfox.chatapp.Repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatRestController {

  @Autowired
  MessageRepo messageRepo;

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ErrorStatus error(MissingServletRequestParameterException e){
    return new ErrorStatus(e.getParameterName());
  }

  @PostMapping("/api/message/receive")
  public void receiveMessage(@RequestBody IncomingMessage mess ){
    messageRepo.save(new Message(mess.getMessage().getUsername(), mess.getMessage().getText(), mess.getMessage().getId(), mess.getMessage().getTimestamp()));
  }

}
