package ru.ruslan.animals.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ruslan.animals.dto.request.OwnerRegisterDto;
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
}
