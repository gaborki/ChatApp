package com.greenfox.chatapp.Controller;


import com.greenfox.chatapp.Module.Client;
import com.greenfox.chatapp.Module.Message;
import com.greenfox.chatapp.Module.JsonMessage;
import com.greenfox.chatapp.Module.Status;
import com.greenfox.chatapp.Repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChatRestController {

  @Autowired
  MessageRepo messageRepo;
  @Autowired
  JsonMessage jsonMessage;

  RestTemplate restTemplate = new RestTemplate();

  @CrossOrigin("*")
  @PostMapping("/api/message/receive")
  public Status receiveMessage(@RequestBody JsonMessage mess) {
    if (!mess.getClient().getId().equals("gabki")) {
      Message toSend = new Message(mess.getMessage().getUsername(), mess.getMessage().getText(), mess.getMessage().getId(), mess.getMessage().getTimestamp());
      messageRepo.save(toSend);
      jsonMessage.setMessage(toSend);
      jsonMessage.setClient(new Client(mess.getClient().getId()));
      restTemplate.postForObject(MainController.getChatAppPeerAddress(), jsonMessage, JsonMessage.class);
    }
    return new Status();
  }
}
