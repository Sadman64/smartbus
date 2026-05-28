package com.smartbus.smartbus.controller;

import com.smartbus.smartbus.model.Bus;
import com.smartbus.smartbus.service.BusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
@Tag(name = "Buses")
public class BusController {

    private final BusService busService;

    @GetMapping
    @Operation(summary = "Получить все автобусы")
    public List<Bus> getAll() {
        return busService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить автобус по ID")
    public Bus getById(@PathVariable Long id) {
        return busService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Создать автобус")
    public Bus create(@RequestBody Bus bus) {
        return busService.create(bus);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить автобус")
    public Bus update(@PathVariable Long id, @RequestBody Bus bus) {
        return busService.update(id, bus);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить автобус")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        busService.delete(id);
        return ResponseEntity.noContent().build();
    }
}