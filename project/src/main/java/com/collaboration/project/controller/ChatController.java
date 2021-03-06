package com.collaboration.project.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.collaboration.project.model.Message;
import com.collaboration.project.model.OutputMessage;

@Controller
@RequestMapping("/")
public class ChatController {

  
    
  @MessageMapping("/chat")
  @SendTo("/topic/message")
  public OutputMessage sendMessage( Message message) {
	 // System.out.println(message);
    return new OutputMessage(message, new Date());
  }
}
