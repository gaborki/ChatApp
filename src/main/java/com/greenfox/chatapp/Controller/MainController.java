package com.greenfox.chatapp.Controller;

import com.greenfox.chatapp.Module.Log;
import com.greenfox.chatapp.Repository.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @Autowired
  LogRepo logRepo;

  @RequestMapping("/")
  public String indexPage(){
    logRepo.save(new Log());
    return "index";
  }


}
