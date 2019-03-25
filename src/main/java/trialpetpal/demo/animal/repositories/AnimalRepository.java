package trialpetpal.demo.animal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trialpetpal.demo.animal.models.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
