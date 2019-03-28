package com.greenfoxacademy.petpal.chat.services;

import com.greenfoxacademy.petpal.chat.models.ChatMessage;
import com.greenfoxacademy.petpal.chat.repositories.MessageRepository;
import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {
  private MessageRepository messageRepository;

  @Autowired
  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public void saveMessage(ChatMessage chatMessage) throws AnimalIdNotFoundException {
    messageRepository.save(chatMessage);
  }
}
