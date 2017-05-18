package com.greenfox.chatapp.Controller;

import com.greenfox.chatapp.Module.Log;
import com.greenfox.chatapp.Repository.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  private final static String CHAT_APP_LOGLEVEL = "INFO";
  private final static String CHAT_APP_UNIQUE_ID = "";
  private final static String CHAT_APP_PEER_ADDRESS = "";


  @Autowired
  LogRepo logRepo;

  @RequestMapping(value = "/")
  public String indexPage(){

    logRepo.save(new Log("/", "/GET", CHAT_APP_LOGLEVEL));
    return "index";
  }

}
