package com.tomi.fexapp.repository;

import com.tomi.fexapp.entity.ConversionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ConversionRecordRepository extends JpaRepository<ConversionRecord, UUID>,
        JpaSpecificationExecutor<ConversionRecord> {
}
