package trialpetpal.demo.animal;


import trialpetpal.demo.AbstractFactory;
import trialpetpal.demo.animal.models.Animal;

public class AnimalFactory implements AbstractFactory<Animal, AnimalType> {

  @Override
  public Animal create(AnimalType animalType) {
    return animalType.createAnimal();
  }
}
