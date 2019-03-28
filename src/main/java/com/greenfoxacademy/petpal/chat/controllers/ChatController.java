package com.greenfoxacademy.petpal.chat.controllers;

import com.greenfoxacademy.petpal.chat.models.ChatMessage;
import com.greenfoxacademy.petpal.chat.services.OwnChatService;
import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import com.greenfoxacademy.petpal.exception.UserNotFoundException;
import com.greenfoxacademy.petpal.users.models.PrivateUser;
import com.greenfoxacademy.petpal.users.services.PrivateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChatController {

  private OwnChatService ownChatService;
  private PrivateUserService parentUserService;

  @Autowired
  public ChatController(OwnChatService ownChatService, PrivateUserService parentUserService) {
    this.ownChatService = ownChatService;
    this.parentUserService = parentUserService;
  }

  @GetMapping(value = "/chats")
  public ResponseEntity getOwnChats(Authentication authentication) throws AnimalIdNotFoundException {
    PrivateUser parentUser = parentUserService.getUserFromAuth(authentication).orElseThrow(() -> new AnimalIdNotFoundException(("There is no Animal with such ID")));
    return ResponseEntity.ok(parentUser.getOwnChats());
  }

  /// Asd
  //Todo: Rename!!!
  @GetMapping(value = "/chats/{chatId}")
  public ResponseEntity getOwnChat(@PathVariable Long chatId) throws AnimalIdNotFoundException {
    return ResponseEntity.ok(ownChatService.findById(chatId));
  }

  @PostMapping(value = "/chats/{chatId}")
  public ResponseEntity sendMessage(@PathVariable Long chatId, ChatMessage chatMessage, Authentication authentication) throws Throwable {
    PrivateUser parentUser = parentUserService.getUserFromAuth(authentication).orElseThrow(() -> new AnimalIdNotFoundException(("There is no Animal with such ID")));
    ownChatService.saveChat(chatMessage, chatId, parentUser);
    return ResponseEntity.ok(ownChatService.findById(chatId));
  }
}
