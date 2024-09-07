package ru.ruslan.animals.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ruslan.animals.dto.request.AnimalPutDto;
import ru.ruslan.animals.dto.response.AnimalRandomDto;
import ru.ruslan.animals.dto.response.ApiResponse;
import ru.ruslan.animals.service.AnimalService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping
    ResponseEntity<ApiResponse<Void>> addAnimal(@Valid @RequestBody AnimalPutDto animalPutDto) {
        animalService.addAnimal(animalPutDto);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @GetMapping("/random")
    ResponseEntity<ApiResponse<AnimalRandomDto>> getRandomAnimal() {
        AnimalRandomDto animal = animalService.getRandom();
        return ResponseEntity.ok(ApiResponse.success(animal));
    }
}
