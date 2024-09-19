package ru.ruslan.animals.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ruslan.animals.dto.request.OwnerEditDto;
import ru.ruslan.animals.dto.request.OwnerRegisterDto;
import ru.ruslan.animals.dto.response.OwnerDto;
import ru.ruslan.animals.mapping.config.MapstructConfig;
import ru.ruslan.animals.model.Owner;

@Mapper(config = MapstructConfig.class)
public interface OwnerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "country", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "animals", ignore = true)
    Owner ownerRegisterDtoToOwner(OwnerRegisterDto ownerRegisterDto);

    OwnerDto ownerToOwnerDto(Owner owner);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "animals", ignore = true)
    void updateOwnerWithOwnerEditDto(@MappingTarget Owner owner, OwnerEditDto ownerEditDto);
}
