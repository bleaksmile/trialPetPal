package com.greenfoxacademy.petpal.chat.models;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.users.models.PrivateUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnChat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany
  @JoinTable(
          name = "parent_users_own_chats",
          joinColumns = @JoinColumn(
                  name = "own_chat_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(
                  name = "parent_user_id", referencedColumnName = "id"))
  private Set<PrivateUser> users;

  private Long unseen;

  @OneToMany(mappedBy = "ownChat", cascade = CascadeType.PERSIST)
  private List<ChatMessage> messages;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  @JoinColumn(name = "animal_id")
  private Animal animal;
}
