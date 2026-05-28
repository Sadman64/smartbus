package com.smartbus.smartbus.service;

import com.smartbus.smartbus.model.Bus;
import com.smartbus.smartbus.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;

    public List<Bus> getAll() {
        return busRepository.findAll();
    }

    public Bus getById(Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found: " + id));
    }

    public Bus create(Bus bus) {
        return busRepository.save(bus);
    }

    public Bus update(Long id, Bus updated) {
        Bus bus = getById(id);
        bus.setModel(updated.getModel());
        bus.setLocation(updated.getLocation());
        bus.setModeType(updated.getModeType());
        return busRepository.save(bus);
    }

    public void delete(Long id) {
        busRepository.deleteById(id);
    }
}