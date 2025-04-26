package ru.fedotov;

import java.math.BigInteger;

class TaskBatch {
    private String workerIp;
    private final BigInteger start;
    private final BigInteger end;
    private final long startTime;

    public TaskBatch(String workerIp, BigInteger start, BigInteger end) {
        this.workerIp = workerIp;
        this.start = start;
        this.end = end;
        this.startTime = System.currentTimeMillis();
    }


    public String getWorkerIp() { return workerIp; }
    public void setWorkerIp(String workerIp) { this.workerIp = workerIp; }
    public BigInteger getStart() { return start; }
    public BigInteger getEnd() { return end; }
    public long getStartTime() { return startTime; }

    @Override
    public String toString() {
        return String.format("Task[%d-%d]", start, end);
    }
}