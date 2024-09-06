package ru.ruslan.animals.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ruslan.animals.dto.request.AnimalPutDto;
import ru.ruslan.animals.dto.response.AnimalResponseDto;
import ru.ruslan.animals.mapping.config.MapstructConfig;
import ru.ruslan.animals.model.Animal;
import ru.ruslan.animals.utility.AnimalTypeUtil;

@Mapper(config = MapstructConfig.class, imports = AnimalTypeUtil.class)
public interface AnimalMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "animalType", expression = "java(AnimalTypeUtil.getAnimalType(animalPutDto.animal()))")
    @Mapping(target = "animalName", source = "name")
    @Mapping(target = "ownerName", source = "owner")
    Animal animalPutDtoToAnimal(AnimalPutDto animalPutDto);

    AnimalResponseDto animalToAnimalResponseDto(Animal animal);
}
