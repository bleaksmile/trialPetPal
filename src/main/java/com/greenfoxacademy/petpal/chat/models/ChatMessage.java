package com.greenfoxacademy.petpal.chat.models;

import com.greenfoxacademy.petpal.users.models.PrivateUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name = "author_id", referencedColumnName = "id")
  private PrivateUser author;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "own_chat_id", referencedColumnName = "id")
  private OwnChat ownChat;

  private String message;

  private Timestamp sentAt;

}
