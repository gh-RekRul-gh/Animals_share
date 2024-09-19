package ru.ruslan.animals.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ruslan.animals.dto.request.OwnerEditDto;
import ru.ruslan.animals.dto.request.OwnerRegisterDto;
import ru.ruslan.animals.dto.request.PasswordDto;
import ru.ruslan.animals.dto.response.ApiResponse;
import ru.ruslan.animals.dto.response.OwnerDto;
import ru.ruslan.animals.service.OwnerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping
    ResponseEntity<ApiResponse<Void>> registerOwner(@Valid @RequestBody OwnerRegisterDto ownerRegisterDto) {
        ownerService.register(ownerRegisterDto);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<OwnerDto>> getOwner(@PathVariable long id) {
        OwnerDto ownerDto = ownerService.getOwner(id);
        return ResponseEntity.ok(ApiResponse.success(ownerDto));
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> editOwner(
            @PathVariable long id,
            @Valid @RequestBody OwnerEditDto ownerEditDto
    ) {
        ownerService.edit(id, ownerEditDto);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @PutMapping("/{id}/password")
    ResponseEntity<ApiResponse<Void>> editOwnerPassword(
            @PathVariable long id,
            @Valid @RequestBody PasswordDto passwordDto
    ) {
        ownerService.editPassword(id, passwordDto);
        return ResponseEntity.ok(ApiResponse.success());
    }
}
