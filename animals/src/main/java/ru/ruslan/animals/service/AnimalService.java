package ru.ruslan.animals.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.ruslan.animals.dto.request.AnimalPutDto;
import ru.ruslan.animals.dto.response.AnimalRandomDto;
import ru.ruslan.animals.exception.AlreadyExistsException;
import ru.ruslan.animals.exception.ZeroEntitiesToGetException;
import ru.ruslan.animals.mapping.AnimalMapper;
import ru.ruslan.animals.model.Animal;
import ru.ruslan.animals.model.Owner;
import ru.ruslan.animals.repository.AnimalRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final OwnerService ownerService;

    public static final String ANIMAL_ALREADY_EXISTS_MESSAGE = "This animal is already added";

    private void validateNewAnimal(Animal newAnimal) {
        if (Boolean.TRUE.equals(animalRepository.existsByAnimalTypeAndAnimalNameAndOwnerId(
                newAnimal.getAnimalType(), newAnimal.getAnimalName(), newAnimal.getOwner().getId()
        ))) {
            throw new AlreadyExistsException(ANIMAL_ALREADY_EXISTS_MESSAGE);
        }
    }

    public void addAnimal(AnimalPutDto animalPutDto) {
        Owner owner = ownerService.findByIdOrElseThrow(animalPutDto.ownerId());
        Animal newAnimal = animalMapper.animalPutDtoToAnimal(animalPutDto, owner);
        validateNewAnimal(newAnimal);
        animalRepository.save(newAnimal);
        log.info("New animal was added: {}", newAnimal.toString());
    }

    public AnimalRandomDto getRandom() {
        Animal animalRandom = animalRepository.getRandomAnimal()
                .orElseThrow(() -> new ZeroEntitiesToGetException("Could not get any animal for random get"));
        return animalMapper.animalToAnimalRandomDto(animalRandom);
    }
}
