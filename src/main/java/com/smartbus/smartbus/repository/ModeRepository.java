package com.smartbus.smartbus.repository;

import com.smartbus.smartbus.model.Mode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeRepository extends JpaRepository<Mode, Long> {
}