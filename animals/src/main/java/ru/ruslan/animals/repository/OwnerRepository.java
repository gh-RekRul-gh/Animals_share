package ru.ruslan.animals.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ruslan.animals.model.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    boolean existsByEmail(String email);
}
