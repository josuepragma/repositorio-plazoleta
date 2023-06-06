package com.pragma.smallsquare.dish.infrastructure.input.rest;

import com.pragma.smallsquare.category.application.dto.CategoryResponseDto;
import com.pragma.smallsquare.category.application.handler.ICategoryHandler;
import com.pragma.smallsquare.category.infrastructure.exceptions.CategoryNotFoundException;
import com.pragma.smallsquare.dish.application.dto.DishRequestDto;
import com.pragma.smallsquare.dish.application.dto.DishRequestDto2Update;
import com.pragma.smallsquare.dish.application.dto.DishResponseDto;
import com.pragma.smallsquare.dish.application.handler.IDishHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;
    private final ICategoryHandler categoryHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveDish(@Valid @RequestBody DishRequestDto dishRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryHandler.getCategoryDtoByName(dishRequestDto.getCategory());
        if (categoryResponseDto == null) {
            throw new CategoryNotFoundException("CATEGORY field doesn't exists in DB");
        }

        dishHandler.saveDishDto(dishRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<DishResponseDto>> getAllDishes() {
        return ResponseEntity.ok(dishHandler.getAllDishesDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponseDto> getDishById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(dishHandler.getDishDtoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDish(@Valid @RequestBody DishRequestDto2Update dishRequestDto2Update,
                                           @PathVariable(name = "id") Integer id) {
        dishHandler.updateDishDto(dishRequestDto2Update, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable(name = "id") Integer id) {
        dishHandler.deleteDishDtoById(id);

        return ResponseEntity.noContent().build();
    }

}
