package com.deniz.appian.processmining.integration;

import com.appian.connectedsystems.templateframework.sdk.configuration.SimpleConfiguration;
import com.appian.connectedsystems.templateframework.sdk.diagnostics.IntegrationDesignerDiagnostic;
import com.appian.connectedsystems.templateframework.sdk.integration.SimpleIntegrationTemplate;
import com.appian.connectedsystems.templateframework.sdk.integration.IntegrationResponse;
import com.appian.connectedsystems.templateframework.sdk.runtime.ExecutionContext;
import com.deniz.appian.processmining.model.ProcessEvent;
import com.deniz.appian.processmining.service.EventAggregator;
import com.deniz.appian.processmining.service.MetricsCalculator;

import java.util.*;

public class EventAggregationIntegration extends SimpleIntegrationTemplate {

    @Override
    protected IntegrationResponse execute(
            SimpleConfiguration integrationConfiguration,
            SimpleConfiguration connectedSystemConfiguration,
            ExecutionContext executionContext) {

        long start = System.nanoTime();

       // Mock process events simulating a real execution trace
        List<ProcessEvent> events = List.of(
                new ProcessEvent("A1", "Start", 100),
                new ProcessEvent("A1", "Approve", 160),
                new ProcessEvent("A1", "End", 240)
        );

        EventAggregator aggregator = new EventAggregator();
        MetricsCalculator metrics = new MetricsCalculator();

        Map<String, List<ProcessEvent>> grouped = aggregator.groupByCase(events);
        List<ProcessEvent> caseEvents = grouped.get("A1");

        Map<String, Object> result = new HashMap<>();
        result.put("cycleTime", metrics.calculateCycleTime(caseEvents));
        result.put("bottleneckActivity", metrics.findSlowestActivity(caseEvents));

        long duration = System.nanoTime() - start;
        /* 
        result.put("executionTimeNs", duration);
        return result; */

        return IntegrationResponse.forSuccess(result)
                .withDiagnostic(
                        IntegrationDesignerDiagnostic.builder()
                                .addExecutionTimeDiagnostic(duration)
                                .build()
                )
                .build();
    }
}