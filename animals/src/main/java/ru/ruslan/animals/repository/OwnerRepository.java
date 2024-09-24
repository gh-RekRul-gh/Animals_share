package ru.ruslan.animals.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ruslan.animals.model.Owner;

import java.util.Optional;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    boolean existsByEmail(String email);

    @Query("SELECT o FROM Owner o JOIN FETCH o.animals WHERE o.id = :id")
    Optional<Owner> findByIdWithAnimals(@Param("id") long id);
}
