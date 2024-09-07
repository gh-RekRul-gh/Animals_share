package ru.ruslan.animals.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ruslan.animals.dto.request.OwnerRegisterDto;
import ru.ruslan.animals.dto.response.ApiResponse;
import ru.ruslan.animals.service.OwnerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping
    ResponseEntity<ApiResponse<Void>> registerUser(@Valid @RequestBody OwnerRegisterDto ownerRegisterDto) {
        ownerService.register(ownerRegisterDto);
        return ResponseEntity.ok(ApiResponse.success());
    }
}
