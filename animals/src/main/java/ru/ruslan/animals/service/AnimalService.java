package ru.ruslan.animals.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.ruslan.animals.dto.request.AnimalPutDto;
import ru.ruslan.animals.dto.response.AnimalResponseDto;
import ru.ruslan.animals.exception.AlreadyExistsException;
import ru.ruslan.animals.exception.ZeroEntitiesToGetException;
import ru.ruslan.animals.mapping.AnimalMapper;
import ru.ruslan.animals.model.Animal;
import ru.ruslan.animals.repository.AnimalRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    public static final String ANIMAL_ALREADY_EXISTS_MESSAGE = "This animal was already added";

    private void validateNewAnimal(Animal newAnimal) {
        if (Boolean.TRUE.equals(animalRepository.existsByAnimalNameAndOwnerNameAndCountryAndCity(
                newAnimal.getAnimalName(), newAnimal.getOwnerName(),
                newAnimal.getCountry(), newAnimal.getCity()
        ))) {
            throw new AlreadyExistsException(ANIMAL_ALREADY_EXISTS_MESSAGE);
        }
    }

    public void addAnimal(AnimalPutDto animalPutDto) {
        Animal newAnimal = animalMapper.animalPutDtoToAnimal(animalPutDto);
        validateNewAnimal(newAnimal);
        animalRepository.save(newAnimal);
        log.info("New animal was added: {}", newAnimal.toString());
    }

    public AnimalResponseDto getRandom() {
        Animal animalRandom = animalRepository.getRandomAnimal()
                .orElseThrow(() -> new ZeroEntitiesToGetException("Could not get any animal for random get"));
        return animalMapper.animalToAnimalResponseDto(animalRandom);
    }
}
