package com.company.tracktime.controller;

import com.company.tracktime.model.TimeRecord;
import com.company.tracktime.repository.TimeRecordsCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeTrackController {

    private TimeRecordsCrudRepository timeRecordsCrudRepository;

    @Autowired
    public TimeTrackController(TimeRecordsCrudRepository timeRecordsCrudRepository) {
        this.timeRecordsCrudRepository = timeRecordsCrudRepository;
    }

    @GetMapping("/getTimeRecords")
    @Transactional
    public List<TimeRecord> getTimeRecordsByMethodName(@RequestParam(required = true) String methodName) {
        return timeRecordsCrudRepository.findByMethodName(methodName);
    }

    @GetMapping("/getTotalTimeSync")
    @Transactional
    public Long getTotalTimeSync() {
        return timeRecordsCrudRepository.getTotalTimeSync();
    }

    @GetMapping("/getTotalTimeAsync")
    @Transactional
    public Long getTotalTimeAsync() {
        return timeRecordsCrudRepository.getTotalTimeAsync();
    }

}
