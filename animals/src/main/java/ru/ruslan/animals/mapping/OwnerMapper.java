package ru.ruslan.animals.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.ruslan.animals.dto.request.OwnerEditDto;
import ru.ruslan.animals.dto.request.OwnerRegisterDto;
import ru.ruslan.animals.dto.response.OwnerDto;
import ru.ruslan.animals.mapping.config.MapstructConfig;
import ru.ruslan.animals.model.Owner;

@Mapper(config = MapstructConfig.class)
public interface OwnerMapper {
    Owner ownerRegisterDtoToOwner(OwnerRegisterDto ownerRegisterDto);

    OwnerDto ownerToOwnerDto(Owner owner);

    void updateOwnerWithOwnerEditDto(@MappingTarget Owner owner, OwnerEditDto ownerEditDto);
}
