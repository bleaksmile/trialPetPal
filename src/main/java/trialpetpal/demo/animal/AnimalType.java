package trialpetpal.demo.animal;


import trialpetpal.demo.animal.models.Animal;
import trialpetpal.demo.animal.models.Cat;
import trialpetpal.demo.animal.models.Dog;

public enum AnimalType {

  dog {
    @Override
    public Animal createAnimal() {
      return new Dog();
    }
  },

  cat {
    @Override
    public Animal createAnimal() {
      return new Cat();
    }
  };

  public Animal createAnimal() {
    return null;
  }
}
