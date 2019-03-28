package com.greenfoxacademy.petpal.chat.repositories;

import com.greenfoxacademy.petpal.chat.models.OwnChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnChatRepository extends JpaRepository<OwnChat, Long> {
}
