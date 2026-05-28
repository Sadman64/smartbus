package com.smartbus.smartbus.service;

import com.smartbus.smartbus.model.Bus;
import com.smartbus.smartbus.model.Sensor;
import com.smartbus.smartbus.repository.BusRepository;
import com.smartbus.smartbus.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final BusRepository busRepository;

    public Sensor addSensorData(Long busId, Sensor sensor) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found: " + busId));
        sensor.setBus(bus);
        sensor.setTimestamp(LocalDateTime.now());
        return sensorRepository.save(sensor);
    }

    public List<Sensor> getLatestByBus(Long busId) {
        return sensorRepository.findByBusIdOrderByTimestampDesc(busId);
    }

    public List<Sensor> getAll() {
        return sensorRepository.findAll();
    }

    public void delete(Long id) {
        sensorRepository.deleteById(id);
    }
}