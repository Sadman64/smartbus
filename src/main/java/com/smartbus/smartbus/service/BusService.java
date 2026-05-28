package com.smartbus.smartbus.service;

import com.smartbus.smartbus.model.Bus;
import com.smartbus.smartbus.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;

    public List<Bus> getAll() {
        log.info("Getting all buses");
        return busRepository.findAll();
    }

    public Bus getById(Long id) {
        log.info("Getting bus by id: {}", id);
        return busRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Bus not found: {}", id);
                    return new RuntimeException("Bus not found: " + id);
                });
    }

    public Bus create(Bus bus) {
        log.info("Creating bus: {}", bus.getModel());
        return busRepository.save(bus);
    }

    public Bus update(Long id, Bus updated) {
        log.info("Updating bus: {}", id);
        Bus bus = getById(id);
        bus.setModel(updated.getModel());
        bus.setLocation(updated.getLocation());
        bus.setModeType(updated.getModeType());
        return busRepository.save(bus);
    }

    public void delete(Long id) {
        log.info("Deleting bus: {}", id);
        busRepository.deleteById(id);
    }
}