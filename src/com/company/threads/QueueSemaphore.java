package com.company.threads;

public class QueueSemaphore {

    private String lastThread = "SequentalCleaner";
    private boolean holded = false;
    private boolean isLTRepairer = false;

    synchronized void acquire(Runnable runnable) throws InterruptedException {
        String[] classP = runnable.getClass().getName().split("\\.");
        while (holded || lastThread.equals(classP[classP.length - 1])) {
            wait();
        }
        holded = true;
        lastThread = classP[classP.length - 1];


    }

    synchronized void release() {
        holded = false;
        notifyAll();
    }

}