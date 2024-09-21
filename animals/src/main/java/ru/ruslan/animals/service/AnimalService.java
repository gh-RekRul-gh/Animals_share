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

    public static final String ANIMAL_ALREADY_EXISTS_MESSAGE = "Animal already exists: %s";
    public static final String RANDOM_ANIMAL_NOT_FOUND =
            "Could not get any random animal. Probably, there are no animals saved";

    private void validateNewAnimal(Animal newAnimal) {
        if (animalRepository.existsByAnimalTypeAndAnimalNameAndOwnerId(
                newAnimal.getAnimalType(), newAnimal.getAnimalName(), newAnimal.getOwner().getId()
        )) {
            String warnMessage = String.format(ANIMAL_ALREADY_EXISTS_MESSAGE, newAnimal);
            log.warn(warnMessage);
            throw new AlreadyExistsException(warnMessage);
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
                .orElseThrow(() -> {
                    String warnMessage = RANDOM_ANIMAL_NOT_FOUND;
                    log.warn(warnMessage);
                    return new ZeroEntitiesToGetException(warnMessage);
                });
        return animalMapper.animalToAnimalRandomDto(animalRandom);
    }
}
