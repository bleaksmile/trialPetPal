package trialpetpal.demo.users.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "GoogleUser")
@DiscriminatorValue("GoogleUser")
@Getter
@Setter
@AllArgsConstructor
public class GoogleUser extends ParentUser {

/*    @ManyToMany(mappedBy = "parentUser", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<Animal> animalsLikedByUser;

    @OneToMany(mappedBy = "parentUser", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<Animal> animalsToAdoptByUser;*/

}
