package com.company.threads;

import com.company.Interfaces.Floor;

/**
 * Created by Opsymonroe on 25.10.2017.
 */
public class Keeper {
    private Floor data;
    private boolean newed = false;

    synchronized public void putData(Floor obj) {
        try {
            while (newed)
                wait();
            data = obj;
            newed = true;
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    synchronized public Floor getData() throws InterruptedException {
        while (!newed)
            wait();
        newed = false;
        notifyAll();
        return data;
    }

    synchronized public void finish() {
        newed = true;
        notifyAll();
    }
}