package com.greenfox.chatapp.Controller;


import com.greenfox.chatapp.Module.Client;
import com.greenfox.chatapp.Module.Message;
import com.greenfox.chatapp.Module.JsonMessage;
import com.greenfox.chatapp.Module.StatusError;
import com.greenfox.chatapp.Module.StatusOk;
import com.greenfox.chatapp.Repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  Message message = new Message();
  RestTemplate restTemplate = new RestTemplate();

  @CrossOrigin("*")
  @PostMapping("/api/message/receive")
  public ResponseEntity receiveMessage(@RequestBody JsonMessage mess) {
    if (!areThereMissingFields(createErrorMessage(mess)) && !mess.getClient().getId().equals("gabki")) {
      createMessageToSave(mess);
      messageRepo.save(message);
      restTemplate.postForObject(MainController.getChatAppPeerAddress(), mess, JsonMessage.class);
    }
    return createReturnMessage(mess);
  }

  public String createErrorMessage(JsonMessage incomingMessage) {
    String missingfields = new String();
    if (incomingMessage.getMessage().getText() == null) {
      missingfields += "message.text";
    }
    if (incomingMessage.getMessage().getId() == 0) {
      missingfields += "message.id";
    }
    if (incomingMessage.getMessage().getUsername() == null) {
      missingfields += "message.username";
    }
    if (incomingMessage.getMessage().getTimestamp() == null) {
      missingfields += "message.timestamp";
    }
    if (incomingMessage.getClient().getId() == null) {
      missingfields += "client.id";
    }
    return missingfields;
  }

  public void createMessageToSave(JsonMessage mess) {
    message = new Message(mess.getMessage().getUsername(), mess.getMessage().getText(),
        mess.getMessage().getId(), mess.getMessage().getTimestamp());
  }

  public void setJsonMessageToSend(JsonMessage mess) {
    jsonMessage.setMessage(message);
    jsonMessage.setClient(new Client(mess.getClient().getId()));
  }

  public boolean areThereMissingFields(String mess) {
    if (mess.length() != 0) {
      return true;
    }
    return false;
  }

  public ResponseEntity<?> createReturnMessage(JsonMessage json){
    if(areThereMissingFields(createErrorMessage(json))){
      return new ResponseEntity(new StatusError(createErrorMessage(json)), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity(new StatusOk(), HttpStatus.OK);
  }
}


