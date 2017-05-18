package com.greenfox.chatapp.Controller;

import com.greenfox.chatapp.Module.Log;
import com.greenfox.chatapp.Module.User;
import com.greenfox.chatapp.Repository.LogRepo;
import com.greenfox.chatapp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  private final static String CHAT_APP_LOGLEVEL = "INFO";
  private final static String CHAT_APP_UNIQUE_ID = "gabki";
  private final static String CHAT_APP_PEER_ADDRESS = "";


  @Autowired
  LogRepo logRepo;
  @Autowired
  UserRepo userRepo;

  @RequestMapping(value = "/")
  public String indexPage(){

    logRepo.save(new Log("/", "/GET", CHAT_APP_LOGLEVEL));
    return "index";
  }

  @RequestMapping(value = "/register")
  public String registerPage(){
    logRepo.save(new Log("/register", "GET", CHAT_APP_LOGLEVEL));
    return "register";
  }


  @PostMapping("/adduser")
  public String addUser(@RequestParam String addUserName, Model model){
    userRepo.save(new User(addUserName));
    return "redirect:/";
  }

}
