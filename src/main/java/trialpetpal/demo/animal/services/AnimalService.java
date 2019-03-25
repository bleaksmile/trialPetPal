package trialpetpal.demo.animal.services;



import trialpetpal.demo.animal.models.Animal;
import trialpetpal.demo.exception.AnimalIdNotFoundException;
import trialpetpal.demo.exception.AnimalIsNullException;

import java.util.List;

public interface AnimalService {

  Animal save(Animal animal) throws AnimalIsNullException;

  void remove(Long id) throws AnimalIdNotFoundException;

  List<Animal> findAll();

  Animal findById(Long id) throws AnimalIdNotFoundException;

  void validateAnimal(Animal animal) throws AnimalIsNullException;

  boolean isAnimalInDB(Animal animal);

}
