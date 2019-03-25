package trialpetpal.demo.users.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import trialpetpal.demo.animal.models.Animal;
import trialpetpal.demo.exception.EmailTakenException;
import trialpetpal.demo.exception.UserNotFoundException;
import trialpetpal.demo.geocode.GeoCode;
import trialpetpal.demo.geocode.GeoCodeService;
import trialpetpal.demo.oauthSecurity.JwtTokenUtil;
import trialpetpal.demo.users.models.PrivateUser;
import trialpetpal.demo.users.repositories.MainUserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class PrivateUserServiceImpl extends ParentUserService<PrivateUser> {

  @Autowired
  private BCryptPasswordEncoder encoder;
  @Autowired
  private GeoCodeService locationService;
  @Autowired
  private MainUserRepository mainUserRepository;
  @Autowired
  private JwtTokenUtil jwtTokenUtil;


  @Override
  public String login(PrivateUser privateUser) throws UserNotFoundException {
    if(!isEmailInDB(privateUser)){
      throw new UserNotFoundException("No such user");
    }
    return jwtTokenUtil.generateToken(privateUser);
  }

  @Override
  public PrivateUser register(PrivateUser privateUser) throws EmailTakenException, UnirestException {

    if (!isEmailInDB(privateUser)) {
      privateUser.setPassword(encoder.encode(privateUser.getPassword()));
      GeoCode geoCode = locationService.generateUserLocationFromAddress(privateUser);
      privateUser.setGeoCode(geoCode);
      return saveUser(privateUser);
    }
    throw new EmailTakenException("Email address already taken, please choose an other one or sign in.");

  }

  @Override
  public Set<Animal> animalsLikedByUser(PrivateUser privateUser) {
    return privateUser.getAnimalsLikedByUser();
  }

  @Override
  public Set<Animal> animalsToAdoptByUser(PrivateUser privateUser) {
    return privateUser.getAnimalsToAdoptByUser();
  }

  @Override
  public void addAnimalToAnimalsLikedByUser(Animal animal, PrivateUser privateUser) {
    //TODO: implement the method, scroll down
  }

  @Override
  public void addAnimalToAnimalsToAdoptByUser(Animal animal, PrivateUser privateUser) {
    //TODO: implement the method, scroll down

  }

  @Override
  public void addAnimalToAnimalsOwnedByUser(Animal animal, PrivateUser privateUser) {
    //TODO: implement the method, scroll down

  }



  private List<SimpleGrantedAuthority> getAuthority() {
    return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    PrivateUser user = (PrivateUser) mainUserRepository.findByEmail(username);
      if (user == null) {
        throw new UsernameNotFoundException("Invalid username or password.");
      }
      return new User(user.getEmail(), user.getPassword(), getAuthority());
    }
 }

/*
  @Override
  public void addAnimalToAnimalsLikedByUser(Animal animal, PrivateUser privateUser) throws Throwable {
    if (animal.getAdopted()) {
      throw new AnimalAlreadyAdoptedException("This pet has been already adopted.");
    }
    Set<Animal> animalsLikedByUser = animalsLikedByUser(privateUser.getId());
    animalsLikedByUser.add(animal);
    privateUser.setAnimalsLikedByUser(animalsLikedByUser);
    System.out.println(animalsLikedByUser);
    Set<PrivateUser> privateUsers = animal.getPrivateUser();
    privateUsers.add(privateUser);
    animal.setPrivateUser(privateUsers);
    saveUser(privateUser);
  }

  public void addAnimalToAnimalsToAdoptByUser(Animal animal, PrivateUser privateUser) throws Throwable {
    if (animal.getAdopted()) {
      throw new AnimalAlreadyAdoptedException("This pet has been already adopted.");
    }
    Set<Animal> animalsToAdoptByUser = animalsToAdoptByUser(privateUser.getId());
    animal.setAdopted(true);
    animalsToAdoptByUser.add(animal);
    //privateUser.setAnimalsToAdoptByUser(animalsToAdoptByUser);
    saveUser(privateUser);
  }

  @Override
  public void addAnimalToAnimalsOwnedByUser(Animal animal, PrivateUser privateUser) throws Throwable {
    Set<Animal> animalsOwnedByUser = animalsOwnedByUser(privateUser.getId());
    animalsOwnedByUser.add(animal);
    privateUser.setOwnedAnimalsByUser(animalsOwnedByUser);
    saveUser(privateUser);
  }
  */

