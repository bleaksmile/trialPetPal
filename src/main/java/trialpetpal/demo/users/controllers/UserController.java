package trialpetpal.demo.users.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import trialpetpal.demo.exception.EmailTakenException;
import trialpetpal.demo.exception.UserIsNullException;
import trialpetpal.demo.oauthSecurity.Token;
import trialpetpal.demo.users.models.*;
import trialpetpal.demo.users.models.dtos.LoginUserDTO;
import trialpetpal.demo.users.models.dtos.RegisterUserDTO;
import trialpetpal.demo.users.models.dtos.UserDTO;
import trialpetpal.demo.users.services.ParentUserService;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class UserController {

  private ParentUserService parentUserService;
  private ModelMapper modelMapper = new ModelMapper();

  @Autowired
  public UserController(ParentUserService userDetailsService) {
    this.parentUserService = userDetailsService;
  }

  @PostMapping("/register/user")
  public ResponseEntity registerUser(@Valid @RequestBody RegisterUserDTO registerUserDTO) throws UserIsNullException, EmailTakenException, UnirestException {
   PrivateUser privateUser = modelMapper.map(registerUserDTO, PrivateUser.class);
   parentUserService.register(privateUser);
    return ResponseEntity.ok(registerUserDTO.getEmail());
  }

  @PostMapping("/register/organization")
  public ResponseEntity registerOrganisation(@Valid @RequestBody Organisation organisation) throws UserIsNullException, UnirestException, EmailTakenException {
    parentUserService.register(organisation);
    return ResponseEntity.ok().body(modelMapper.map(organisation, UserDTO.class));
  }

/*  @PostMapping("/oauth2/authorize/google")
  public ResponseEntity loginGoogleUser(GoogleUser googleUser) throws UserNotFoundException {
    String token = parentUserService.login(googleUser);
    return ResponseEntity.ok().body(token);
  }*/

  @PostMapping("/login/user")
  public ResponseEntity loginPrivateUser(@Valid @RequestBody LoginUserDTO loginUserDTO ) throws Throwable {
    PrivateUser privateUser = (PrivateUser) parentUserService.findByEmail(loginUserDTO.getEmail());
    Token token = new Token(parentUserService.login(privateUser));
    return ResponseEntity.ok().body(token);
  }

  @PutMapping("/user/{id}")
  public ResponseEntity changePassword(Authentication authentication, @RequestBody String password) throws Throwable {
    ParentUser user = (ParentUser) parentUserService.getUserFromAuth(authentication);
    //TODO: separate usertype, pw not applicable for GoogleU, changePW method should be implemented in service with pw hash

    return ResponseEntity.ok().build();
  }

  /*@PutMapping("/organisation/{id}")
  public ResponseEntity changeOrganisation(@PathVariable Long id, Organisation organisation) {
    Organisation organisationToChange = organisationService.findById(id);
    return ResponseEntity.ok(organisationService.save(organisationToChange));
    return null;
  }*/

  @GetMapping("/pets/liked")
  public ResponseEntity likedPets(Authentication authentication) throws Throwable {
    ParentUser parentUser =  parentUserService.getUserFromAuth(authentication);
    return ResponseEntity.ok(parentUserService.animalsLikedByUser(parentUser));
  }

  @GetMapping("/pets/adoptable")
  public ResponseEntity adoptedPets(Authentication authentication) throws Throwable {
    ParentUser parentUser =  parentUserService.getUserFromAuth(authentication);
    return ResponseEntity.ok(parentUserService.animalsToAdoptByUser(parentUser));
  }

  @GetMapping("/pets/owned")
  public ResponseEntity ownedPets(Authentication authentication) throws Throwable {
    ParentUser parentUser =  parentUserService.getUserFromAuth(authentication);
    return ResponseEntity.ok(parentUserService.animalsOwnedByUser(parentUser));
  }
  //TODO: delete pet from all of the lists AND delete pet for good (4 endpoints)
}