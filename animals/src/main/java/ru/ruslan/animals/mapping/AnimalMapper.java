package ru.ruslan.animals.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ruslan.animals.dto.request.AnimalPutDto;
import ru.ruslan.animals.dto.response.AnimalRandomDto;
import ru.ruslan.animals.mapping.config.MapstructConfig;
import ru.ruslan.animals.model.Animal;
import ru.ruslan.animals.model.Owner;
import ru.ruslan.animals.utility.AnimalUtil;
import ru.ruslan.animals.utility.OwnerUtil;

@Mapper(config = MapstructConfig.class, imports = {AnimalUtil.class, OwnerUtil.class})
public interface AnimalMapper {
    @Mapping(target = "animalType", expression = "java(AnimalUtil.getAnimalType(animalPutDto.animalType()))")
    @Mapping(target = "owner", source = "owner")
    Animal animalPutDtoToAnimal(AnimalPutDto animalPutDto, Owner owner);

    @Mapping(target = "ownerName", expression = "java(OwnerUtil.getFullName(animal.getOwner()))")
    @Mapping(target = "country", source = "animal.owner.country")
    @Mapping(target = "city", source = "animal.owner.city")
    AnimalRandomDto animalToAnimalRandomDto(Animal animal);
}
