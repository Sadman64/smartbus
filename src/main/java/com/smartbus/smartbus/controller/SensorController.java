package com.smartbus.smartbus.controller;

import com.smartbus.smartbus.model.Sensor;
import com.smartbus.smartbus.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
@Tag(name = "Sensors")
public class SensorController {

    private final SensorService sensorService;

    @PostMapping("/{busId}")
    @Operation(summary = "Добавить данные с датчика")
    public Sensor addData(@PathVariable Long busId, @RequestBody Sensor sensor) {
        return sensorService.addSensorData(busId, sensor);
    }

    @GetMapping("/cabin/status")
    @Operation(summary = "Последнее состояние датчиков автобуса")
    public List<Sensor> getCabinStatus(@RequestParam Long busId) {
        return sensorService.getLatestByBus(busId);
    }

    @GetMapping
    @Operation(summary = "Все данные датчиков")
    public List<Sensor> getAll() {
        return sensorService.getAll();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить запись датчика")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sensorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}