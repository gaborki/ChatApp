package com.greenfox.chatapp.Controller;

import com.greenfox.chatapp.Module.AppUser;
import com.greenfox.chatapp.Module.Client;
import com.greenfox.chatapp.Module.JsonMessage;
import com.greenfox.chatapp.Module.Log;
import com.greenfox.chatapp.Module.Message;
import com.greenfox.chatapp.Module.StatusOk;
import com.greenfox.chatapp.Repository.LogRepo;
import com.greenfox.chatapp.Repository.MessageRepo;
import com.greenfox.chatapp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

  private final static String CHAT_APP_LOGLEVEL = "INFO";
  private static String CHAT_APP_UNIQUE_ID = "gaborki";
  private static String CHAT_APP_PEER_ADDRESS = "https://nokecskes-p2p.herokuapp.com/api/message/receive";

  public static void setChatAppUniqueId(String chatAppUniqueId) {
    CHAT_APP_UNIQUE_ID = chatAppUniqueId;
  }

  public static void setChatAppPeerAddress(String chatAppPeerAddress) {
    CHAT_APP_PEER_ADDRESS = chatAppPeerAddress;
  }

  public static String getChatAppLoglevel() {
    return CHAT_APP_LOGLEVEL;
  }

  public static String getChatAppUniqueId() {
    return CHAT_APP_UNIQUE_ID;
  }

  public static String getChatAppPeerAddress() {
    return CHAT_APP_PEER_ADDRESS;
  }

  @Autowired
  LogRepo logRepo;
  @Autowired
  UserRepo userRepo;
  @Autowired
  MessageRepo messageRepo;
  @Autowired
  AppUser appUser;
  @Autowired
  JsonMessage jsonMessage;

  RestTemplate restTemplate = new RestTemplate();

  @ExceptionHandler(HttpServerErrorException.class)
  public String exeptionHand(HttpServerErrorException e){
    e.printStackTrace();
    return "redirect:/";
  }

  @RequestMapping(value = "/")
  public String indexPage(Model model) {
    System.out.println(messageRepo.findAllByOrderByTimestampDesc());
    if (appUser.getUsername() == null) {
      logRepo.save(new Log("/", "/GET", CHAT_APP_LOGLEVEL, "no parameter"));
      return "redirect:/register";
    } else {
      model.addAttribute(appUser);
      model.addAttribute("messageList", messageRepo.findAllByOrderByTimestampDesc());
      logRepo.save(new Log("/", "/GET", CHAT_APP_LOGLEVEL, "no parameter"));
      return "index2";
    }
  }

  @RequestMapping(value = "/register")
  public String registerPage() {
    logRepo.save(new Log("/register", "GET", CHAT_APP_LOGLEVEL, "no parameter"));
    return "register";
  }

  @GetMapping("/adduser")
  public String addUser(@RequestParam("addUserName") String addUserName) {
    logRepo.save(new Log("/register", "GET", CHAT_APP_LOGLEVEL, addUserName));
    if (addUserName.equals("")) {
      return "noUserNameGiven";
    } else {
      appUser.setUsername(addUserName);
      appUser.setId(1);
      userRepo.save(appUser);
      setChatAppUniqueId(addUserName);
      return "redirect:/";
    }
  }

  @GetMapping("/sendmessage")
  public String sendMessage(@RequestParam("messa") String mes){
    logRepo.save(new Log("/sendmessage", "GET", CHAT_APP_LOGLEVEL, mes));
    Message messageToSend = new Message(appUser.getUsername(), mes);
    messageRepo.save(messageToSend);
    jsonMessage.setMessage(new Message(CHAT_APP_UNIQUE_ID, mes));
    jsonMessage.setClient(new Client(CHAT_APP_UNIQUE_ID));
    restTemplate.postForObject(CHAT_APP_PEER_ADDRESS, jsonMessage, StatusOk.class);
    return "redirect:/";
  }

  @GetMapping("/deletemessages")
  public String deleteMessages(){
    messageRepo.deleteAll();
    return "redirect:/";
  }
}
