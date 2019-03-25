package trialpetpal.demo.animal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import trialpetpal.demo.animal.models.Animal;
import trialpetpal.demo.animal.models.AnimalDTO;
import trialpetpal.demo.animal.models.Cat;
import trialpetpal.demo.animal.models.Dog;
import trialpetpal.demo.animal.services.AnimalService;
import trialpetpal.demo.exception.AnimalIdNotFoundException;
import trialpetpal.demo.exception.AnimalIsNullException;
import trialpetpal.demo.exception.InvalidTypeException;
import trialpetpal.demo.users.models.ParentUser;
import trialpetpal.demo.users.services.ParentUserService;

@RestController
public class AnimalController {

  private AnimalService animalService;
  private ParentUserService parentUserService;

  @Autowired
  public AnimalController(AnimalService animalService, ParentUserService parentUserService) {
    this.animalService = animalService;
    this.parentUserService = parentUserService;
  }

  @GetMapping("/home/pets")
  public ResponseEntity pets() {
    return ResponseEntity.ok(animalService.findAll());
  }

  @GetMapping("/pet/{id}")
  public ResponseEntity pet(@PathVariable Long id) throws AnimalIdNotFoundException {
    return ResponseEntity.ok(animalService.findById(id));
  }

  @PostMapping("/pet/{id}/like")
  public ResponseEntity like(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = parentUserService.getUserFromAuth(authentication);
    parentUserService.addAnimalToAnimalsLikedByUser(animalService.findById(id), parentUser);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/pet/{id}/toAdopt")
  public ResponseEntity addToAdopt(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = parentUserService.getUserFromAuth(authentication);
    parentUserService.addAnimalToAnimalsToAdoptByUser(animalService.findById(id), parentUser);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/pet")
  public ResponseEntity upload(@RequestBody AnimalDTO animalDTO, Authentication authentication) throws Throwable {
    ParentUser parentUser = parentUserService.getUserFromAuth(authentication);
    ModelMapper modelMapper = new ModelMapper();
    Animal animal;
    if (animalDTO.getType().equals("dog")) {
      animal = modelMapper.map(animalDTO, Dog.class);
    } else if (animalDTO.getType().equals("cat")) {
      animal = modelMapper.map(animalDTO, Cat.class);
    } else {
      throw new InvalidTypeException("Invalid type");
    }
    parentUserService.addAnimalToAnimalsOwnedByUser(animal, parentUser);
    return ResponseEntity.ok().build();
  }

  //PUT /pet/{id} -> ha elcseszted, javíthatod az állat adatait (edited)
  @PutMapping("/pet/{id}")
  public ResponseEntity change(@PathVariable Long id, Authentication authentication, Animal animal) throws AnimalIdNotFoundException, AnimalIsNullException {
    //TODO: modify an animal's details
    //Get animal from frontend WITH ID
    return ResponseEntity.ok().body(animalService.save(animal));
  }

  @DeleteMapping("/pet/{id}/owned")
  public ResponseEntity deleteFromOwned(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = parentUserService.getUserFromAuth(authentication);
    Animal animal = animalService.findById(id);
    return ResponseEntity.ok(parentUser.getOwnedAnimalsByUser().remove(animal));
  }

  @DeleteMapping("/pet/{id}/like")
  public ResponseEntity deleteFromLiked(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = parentUserService.getUserFromAuth(authentication);
    Animal animal = animalService.findById(id);
    //TODO: implement
    return null;
  }

  @DeleteMapping("pet/{id}/adoptable")
  public ResponseEntity deleteFromToAdopt(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = parentUserService.getUserFromAuth(authentication);
    Animal animal = animalService.findById(id);
    //return ResponseEntity.ok(privateUser.getAnimalsToAdoptByUser().remove(animal));
    //TODO: implement
    return null;
  }
}
//TODO: reduce duplications
