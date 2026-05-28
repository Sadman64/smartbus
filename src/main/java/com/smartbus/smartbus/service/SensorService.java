package com.smartbus.smartbus.service;

import com.smartbus.smartbus.model.Bus;
import com.smartbus.smartbus.model.Sensor;
import com.smartbus.smartbus.repository.BusRepository;
import com.smartbus.smartbus.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final BusRepository busRepository;

    public Sensor addSensorData(Long busId, Sensor sensor) {
        log.info("Adding sensor data for bus: {}, type: {}", busId, sensor.getType());
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> {
                    log.error("Bus not found: {}", busId);
                    return new RuntimeException("Bus not found: " + busId);
                });
        sensor.setBus(bus);
        sensor.setTimestamp(LocalDateTime.now());
        return sensorRepository.save(sensor);
    }

    public List<Sensor> getLatestByBus(Long busId) {
        log.info("Getting latest sensor data for bus: {}", busId);
        return sensorRepository.findByBusIdOrderByTimestampDesc(busId);
    }

    public List<Sensor> getAll() {
        log.info("Getting all sensor data");
        return sensorRepository.findAll();
    }

    public void delete(Long id) {
        log.info("Deleting sensor record: {}", id);
        sensorRepository.deleteById(id);
    }
}