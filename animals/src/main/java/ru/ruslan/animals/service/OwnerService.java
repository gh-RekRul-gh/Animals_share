package ru.ruslan.animals.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.ruslan.animals.dto.request.OwnerEditDto;
import ru.ruslan.animals.dto.request.OwnerRegisterDto;
import ru.ruslan.animals.dto.request.PasswordDto;
import ru.ruslan.animals.dto.response.OwnerDto;
import ru.ruslan.animals.exception.AlreadyExistsException;
import ru.ruslan.animals.exception.EntityNotFoundException;
import ru.ruslan.animals.mapping.OwnerMapper;
import ru.ruslan.animals.model.Owner;
import ru.ruslan.animals.repository.OwnerRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public static final String EMAIL_ALREADY_EXISTS_MESSAGE = "Email already exists: %s";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found with id: %s";

    public Owner findByIdOrElseThrow(long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() -> {
                    String warnMessage = String.format(USER_NOT_FOUND_MESSAGE, ownerId);
                    log.warn(warnMessage);
                    return new EntityNotFoundException(warnMessage);
                });
    }

    public void register(OwnerRegisterDto ownerRegisterDto) {
        String newUserEmail = ownerRegisterDto.email();
        if (ownerRepository.existsByEmail(newUserEmail)) {
            String warnMessage = String.format(EMAIL_ALREADY_EXISTS_MESSAGE, newUserEmail);
            log.warn(warnMessage);
            throw new AlreadyExistsException(warnMessage);
        }
        Owner newOwner = ownerMapper.ownerRegisterDtoToOwner(ownerRegisterDto);
        ownerRepository.save(newOwner);
        log.info("New owner was added with email: {}", newOwner.getEmail());
    }

    public OwnerDto getOwner(long ownerId) {
        Owner owner = findByIdOrElseThrow(ownerId);
        return ownerMapper.ownerToOwnerDto(owner);
    }

    public void edit(long ownerId, OwnerEditDto ownerEditDto) {
        Owner owner = findByIdOrElseThrow(ownerId);

        ownerMapper.updateOwnerWithOwnerEditDto(owner, ownerEditDto);
        ownerRepository.save(owner);

        log.info("Owner was edited with email: {}", owner.getEmail());
    }

    public void editPassword(long ownerId, PasswordDto passwordDto) {
        Owner owner = findByIdOrElseThrow(ownerId);

        owner.setPassword(passwordDto.password());
        ownerRepository.save(owner);

        log.info("Owner password was edited with email: {}", owner.getEmail());
    }
}
