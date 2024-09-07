package ru.ruslan.animals.repository;

import org.springframework.data.jpa.repository.Query;
import ru.ruslan.animals.enumeration.AnimalType;
import ru.ruslan.animals.model.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {
    Boolean existsByAnimalTypeAndAnimalNameAndOwnerId(AnimalType animalType, String animaName, Long ownerId);

    @Query("SELECT animal from Animal animal ORDER BY RANDOM() LIMIT 1")
    Optional<Animal> getRandomAnimal();
}
