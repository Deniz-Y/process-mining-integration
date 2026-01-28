package com.deniz.appian.processmining.service;

import com.deniz.appian.processmining.model.ProcessEvent;

import java.util.*;

/**
 * Groups process events by caseId and sorts them by timestamp.
 *
 */

public class EventAggregator {

    public Map<String, List<ProcessEvent>> groupByCase(List<ProcessEvent> events) {
        Map<String, List<ProcessEvent>> cases = new HashMap<>();

        for (ProcessEvent event : events) {
            cases
                .computeIfAbsent(event.getCaseId(), k -> new ArrayList<>())
                .add(event);
        }
  // events inside each case are ordered by timestamp
        cases.values().forEach(list ->
            list.sort(Comparator.comparingLong(ProcessEvent::getTimestamp))
        );

        return cases;
    }
}