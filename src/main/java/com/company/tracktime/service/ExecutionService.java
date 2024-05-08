package com.company.tracktime.service;

import com.company.tracktime.annotation.TrackAsyncTime;
import com.company.tracktime.annotation.TrackTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

import static java.lang.Thread.sleep;

@Service
@Slf4j
public class ExecutionService {

    Random random = new Random();

    @TrackTime
    @Scheduled(initialDelayString = "1000", fixedDelayString = "1000")
    public void runSync() throws InterruptedException {
        sleep(random.nextInt(2000));
    }

    @TrackAsyncTime
    @Async
    @Scheduled(initialDelayString = "1000", fixedDelayString = "1000")
    public void runAsync() throws InterruptedException {
        sleep(random.nextInt(2000));
    }
}
