# Appian Process Mining Integration – Case Study

This repository contains a small backend-focused engineering case study inspired by the Appian platform,
with a focus on process mining concepts and integration-style backend logic.


---

## What this project does

The project simulates a simplified process mining scenario using in-memory event data.

It:
- groups events by process instance (`caseId`)
- sorts events chronologically
- calculates cycle time
- identifies bottleneck activities
- measures internal backend execution time

The output represents the type of structured data that could be consumed by an Appian process.

---

## Project structure and references

The project follows an integration-oriented structure inspired by the official
Appian Integration SDK examples repository:

https://github.com/appian/integration-sdk-examples

This repository was used as a reference for project layout and SDK conventions.
The process mining logic itself (event aggregation and metric calculation) is original
and implemented specifically for this case study.

---

## Example output

```json
{
  "cycleTime": 140,
  "bottleneckActivity": "Approve",
  "executionTimeNs": 120000
}
