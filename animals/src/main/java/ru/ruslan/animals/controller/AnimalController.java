package ru.ruslan.animals.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ruslan.animals.dto.request.AnimalPutDto;
import ru.ruslan.animals.dto.response.AnimalResponseDto;
import ru.ruslan.animals.dto.response.ApiResponse;
import ru.ruslan.animals.service.AnimalService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalService animalService;

    @PutMapping
    ResponseEntity<ApiResponse<Void>> addAnimal(@Valid AnimalPutDto animalPutDto) {
        animalService.addAnimal(animalPutDto);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @GetMapping("/random")
    ResponseEntity<ApiResponse<AnimalResponseDto>> getRandomAnimal() {
        AnimalResponseDto animal = animalService.getRandom();
        return ResponseEntity.ok(ApiResponse.success(animal));
    }
}
