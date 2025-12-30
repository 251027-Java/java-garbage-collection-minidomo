[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/ryfra96S)

# Lab: JVM Memory Analysis

## Goal

Analyze JVM memory usage and configure garbage collection settings for optimal performance.

## Learning Objectives

- Understand JVM memory regions (Heap, Stack, Metaspace)
- Enable and interpret GC logs
- Compare different garbage collectors
- Use JVM tuning flags

## Pre-requisites

- Java 17+ installed
- Basic understanding of Java memory model

## Tasks

### Task 1: Create Memory-Intensive Application

Create a Java application that allocates memory progressively.

**Starter Code Provided**: `MemoryLabApp.java`

### Task 2: Run with GC Logging

Run the application with the following JVM flags:

```bash
java -Xms128m -Xmx256m -Xlog:gc*:file=gc.log:time,uptime MemoryLabApp
```

**Questions to Answer**:

1. How many Minor GCs occurred? 10
2. How many Major/Full GCs occurred? 9
3. What was the longest GC pause time? 6.523ms

### Task 3: Compare Garbage Collectors

Run the application with different GC algorithms:

```bash
# Serial GC
java -XX:+UseSerialGC -Xmx256m -Xlog:gc*:file=gc-serial.log MemoryLabApp

# G1GC
java -XX:+UseG1GC -Xmx256m -Xlog:gc*:file=gc-g1.log MemoryLabApp

# ZGC (Java 11+)
java -XX:+UseZGC -Xmx256m -Xlog:gc*:file=gc-zgc.log MemoryLabApp
```

**Record your observations** in a table:

| GC Type | Total GC Events | Longest Pause | Throughput |
|---------|-----------------|---------------|------------|
| Serial  | 5               | 26.022 ms     | 98.506     |
| G1GC    | 21              | 6.979 ms      | 98.897     |
| ZGC     | 127             | 0.169 ms      | 99.985     |

### Task 4: Tune for Low Latency

Configure G1GC to target a maximum pause time of 100ms:

```bash
java -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -Xmx256m MemoryLabApp
```

**Questions**:

1. Did G1GC achieve the target pause time? It was under, but it was close
2. What trade-offs did you observe? Slightly lower throughput and more pauses

## Deliverables

1. Completed `MemoryLabApp.java`
2. GC log files from each run
3. Answers to all questions in a markdown file

## Starter Code

See `starter_code/MemoryLabApp.java`
