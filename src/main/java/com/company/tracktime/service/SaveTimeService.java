package com.company.tracktime.service;

import com.company.tracktime.model.MethodType;
import com.company.tracktime.model.TimeRecord;
import com.company.tracktime.repository.TimeRecordsCrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class SaveTimeService {

    private TimeRecordsCrudRepository timeRecordsCrudRepository;

    @Autowired
    public SaveTimeService(TimeRecordsCrudRepository timeRecordsCrudRepository) {
        this.timeRecordsCrudRepository = timeRecordsCrudRepository;
    }

    @Transactional
    @Async
    public void saveMethodTimeExecution(String methodName, long timeSpent, MethodType methodType) {
        var timerecord = new TimeRecord(methodName, timeSpent, methodType);
        timeRecordsCrudRepository.save(timerecord);
        log.info(timerecord.toString());
    }
}
