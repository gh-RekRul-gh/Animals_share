package ru.ruslan.animals.repository;

import org.springframework.data.jpa.repository.Query;
import ru.ruslan.animals.enumeration.AnimalType;
import ru.ruslan.animals.model.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {
    boolean existsByAnimalTypeAndAnimalNameAndOwnerId(AnimalType animalType, String animaName, long ownerId);

    @Query("SELECT a FROM Animal a ORDER BY RANDOM() LIMIT 1")
    Optional<Animal> getRandomAnimal();

    @Query("SELECT a FROM Animal a JOIN FETCH a.owner")
    Optional<Animal> findByIdWithOwner(long id);
}
