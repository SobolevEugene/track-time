package com.company.tracktime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TIME_RECORD")
public class TimeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String methodName;

    @Column(nullable = false)
    private Long timeSpent;

    @Column(nullable = false)
    private Integer methodType;

    public TimeRecord(String methodName, long timeSpent, MethodType methodType) {
        this.methodName = methodName;
        this.timeSpent = timeSpent;
        this.methodType = methodType.ordinal();
    }

    @Override
    public String toString() {
        return "TimeRecord{" +
                "methodName='" + methodName + '\'' +
                ", timeSpent=" + timeSpent +
                ", methodType=" + methodType +
                '}';
    }
}
