package com.greenfoxacademy.petpal.chat.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.chat.models.ChatMessage;
import com.greenfoxacademy.petpal.chat.models.OwnChat;
import com.greenfoxacademy.petpal.chat.repositories.OwnChatRepository;
import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import com.greenfoxacademy.petpal.users.models.PrivateUser;
import com.greenfoxacademy.petpal.users.services.PrivateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OwnChatService {
  private OwnChatRepository ownChatRepository;
  private MessageService messageService;
  private PrivateUserService privateUserService;

  @Autowired
  public OwnChatService(OwnChatRepository ownChatRepository, MessageService messageService, PrivateUserService privateUserService) {
    this.ownChatRepository = ownChatRepository;
    this.messageService = messageService;
    this.privateUserService = privateUserService;
  }

  //TODO: Rename!!!!!!
  public OwnChat findById(Long id) throws AnimalIdNotFoundException {
    return ownChatRepository.findById(id)
            .orElseThrow(() -> new AnimalIdNotFoundException(("There is no Animal with such ID")));
  }

  public void saveChat(ChatMessage chatMessage, Long id, PrivateUser parentUser) throws AnimalIdNotFoundException {
    OwnChat ownChat = findById(id);
    List<ChatMessage> messages = ownChat.getMessages();
    messages.add(chatMessage);
    ownChat.setMessages(messages);
    chatMessage.setAuthor(parentUser);
    messageService.saveMessage(chatMessage);
    ownChatRepository.save(ownChat);
  }

  public void createChat(PrivateUser user1, PrivateUser user2, Animal animal) throws UserIsNullException {
    OwnChat ownChat = new OwnChat();
    Set<PrivateUser> users = new HashSet<>();
    users.add(user1);
    users.add(user2);
    ownChat.setUsers(users);
    ownChat.setAnimal(animal);
    Set<OwnChat> firstUserChat = user1.getOwnChats();
    firstUserChat.add(ownChat);
    Set<OwnChat> secondUserChat = user2.getOwnChats();
    secondUserChat.add(ownChat);
    user1.setOwnChats(firstUserChat);
    user2.setOwnChats(secondUserChat);
    ownChatRepository.save(ownChat);
    privateUserService.saveUser(user1);
    privateUserService.saveUser(user2);
  }
}
