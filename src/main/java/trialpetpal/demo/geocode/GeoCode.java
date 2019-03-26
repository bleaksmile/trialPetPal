package trialpetpal.demo.geocode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import trialpetpal.demo.users.models.ParentUser;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GeoCode {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Double lng;
  private Double lat;

 /* @OneToOne(mappedBy = "geoCode", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private ParentUser parentUser;*/
}
