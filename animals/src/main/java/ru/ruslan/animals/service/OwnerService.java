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

    public Owner findByIdOrElseThrow(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + ownerId));
    }

    public void register(OwnerRegisterDto ownerRegisterDto) {
        String newUserEmail = ownerRegisterDto.email();
        if (Boolean.TRUE.equals(ownerRepository.existsByEmail(newUserEmail))) {
            throw new AlreadyExistsException(String.format("Email %s is already taken", newUserEmail));
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
