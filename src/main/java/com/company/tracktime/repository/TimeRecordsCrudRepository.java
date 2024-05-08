package com.company.tracktime.repository;

import com.company.tracktime.model.TimeRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TimeRecordsCrudRepository extends CrudRepository<TimeRecord, UUID> {
    @Query("select sum(tr.timeSpent) from TimeRecord tr where tr.methodType = 0")
    Long getTotalTimeSync();

    @Query("select sum(tr.timeSpent) from TimeRecord tr where tr.methodType = 1")
    Long getTotalTimeAsync();

    List<TimeRecord> findByMethodName(String methodName);
}
