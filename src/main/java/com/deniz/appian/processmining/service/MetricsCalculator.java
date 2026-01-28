package com.deniz.appian.processmining.service;

import com.deniz.appian.processmining.model.ProcessEvent;

import java.util.*;

/**
 * Computes basic process mining metrics such as cycle time
 * and bottleneck activities from ordered process events.
 */

public class MetricsCalculator {

     /**
     * Calculates the cycle time of a process instance.
     *
     * Cycle time = timestamp of last event - timestamp of first event
     */

    public long calculateCycleTime(List<ProcessEvent> events) {
        if (events == null || events.isEmpty()) return 0;
        return events.get(events.size() - 1).getTimestamp()
             - events.get(0).getTimestamp();
    }

     /**
     * Identifies the slowest activity by accumulating
     * time spent between consecutive events.
     */
    public String findSlowestActivity(List<ProcessEvent> events) {
        Map<String, Long> durations = new HashMap<>();

        for (int i = 1; i < events.size(); i++) {
            ProcessEvent prev = events.get(i - 1);
            ProcessEvent curr = events.get(i);

            long delta = curr.getTimestamp() - prev.getTimestamp();
            durations.merge(prev.getActivity(), delta, Long::sum);
        }

        return durations.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }
}