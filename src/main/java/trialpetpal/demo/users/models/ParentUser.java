package trialpetpal.demo.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import trialpetpal.demo.animal.models.Animal;
import trialpetpal.demo.geocode.GeoCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ParentUser {

  public ParentUser(@NotBlank String name) {
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  private String name;
  @Email
  private String email;
  private String phoneNumber;
  @Column
  private String imageUrl;


  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name = "geo_code_id")
  private GeoCode geoCode;
  private String address;
//  TODO address fields

  @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private Set<Animal> ownedAnimalsByUser;

  @ManyToMany(mappedBy = "parentUser", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private Set<Animal> animalsLikedByUser;

  @OneToMany(mappedBy = "parentUserAdopt", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private Set<Animal> animalsToAdoptByUser;

}
