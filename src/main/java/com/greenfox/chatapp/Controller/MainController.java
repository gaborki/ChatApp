package com.greenfox.chatapp.Controller;

import com.greenfox.chatapp.Module.AppUser;
import com.greenfox.chatapp.Module.Client;
import com.greenfox.chatapp.Module.IncomingMessage;
import com.greenfox.chatapp.Module.Log;
import com.greenfox.chatapp.Module.Message;
import com.greenfox.chatapp.Repository.LogRepo;
import com.greenfox.chatapp.Repository.MessageRepo;
import com.greenfox.chatapp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

  private final static String CHAT_APP_LOGLEVEL = "INFO";
  private final static String CHAT_APP_UNIQUE_ID = "gaborki";
  private final static String CHAT_APP_PEER_ADDRESS = "https://greenfox-chat-app.herokuapp.com/api/message/receive";


  @Autowired
  LogRepo logRepo;
  @Autowired
  UserRepo userRepo;
  @Autowired
  MessageRepo messageRepo;
  @Autowired
  AppUser appUser;
  @Autowired
  IncomingMessage incomingMessage;

  RestTemplate restTemplate = new RestTemplate();

  @RequestMapping(value = "/")
  public String indexPage(Model model) {
    if (appUser.getUsername() == null) {
      logRepo.save(new Log("/", "/GET", CHAT_APP_LOGLEVEL, "no parameter"));
      return "redirect:/register";
    } else {
      model.addAttribute(appUser);
      model.addAttribute("messageList", messageRepo.findAll());
      logRepo.save(new Log("/", "/GET", CHAT_APP_LOGLEVEL, "no parameter"));
      return "index";
    }
  }

  @RequestMapping(value = "/register")
  public String registerPage() {
    logRepo.save(new Log("/register", "GET", CHAT_APP_LOGLEVEL, "no parameter"));
    return "register";
  }

  @GetMapping("/adduser")
  public String addUser(@RequestParam("addUserName") String addUserName) {
    System.out.println(addUserName);
    logRepo.save(new Log("/register", "GET", CHAT_APP_LOGLEVEL, addUserName));
    if (addUserName.equals("")) {
      return "error";
    } else {
      appUser.setUsername(addUserName);
      appUser.setId(1);
      userRepo.save(appUser);
      return "redirect:/";
    }
  }

  @GetMapping("/sendmessage")
  public String sendMessage(@RequestParam("message") String message){
    logRepo.save(new Log("/sendmessage", "GET", CHAT_APP_LOGLEVEL, message));
    Message messageToSend = new Message(appUser.getUsername(), message);
    messageRepo.save(messageToSend);
    incomingMessage.setMessage(new Message(message));
    incomingMessage.setClient(new Client(CHAT_APP_UNIQUE_ID));
    restTemplate.postForObject(CHAT_APP_PEER_ADDRESS, incomingMessage, IncomingMessage.class);
    return "redirect:/";
  }
}
