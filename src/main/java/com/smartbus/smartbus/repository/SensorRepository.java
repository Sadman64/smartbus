package com.smartbus.smartbus.repository;

import com.smartbus.smartbus.model.Sensor;
import com.smartbus.smartbus.model.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findByBusIdOrderByTimestampDesc(Long busId);
    List<Sensor> findByBusIdAndType(Long busId, SensorType type);
}