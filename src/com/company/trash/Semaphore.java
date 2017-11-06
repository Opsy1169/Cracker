package com.company.trash;

import com.company.Interfaces.Floor;

/**
 * Created by Opsymonroe on 25.10.2017.
 */
public class Semaphore {
    private boolean canWork = true;
    Floor floor;
    public Semaphore(Floor floor){
        this.floor = floor;
    }
    public synchronized void beginClean() throws InterruptedException{
        while (canWork){
            wait();
        }
    }
    public synchronized void endClean() {
        canWork = true;
        notifyAll();
    }
    public synchronized void beginRepair() throws InterruptedException{
        while(!canWork){
            wait();
        }
    }
    public synchronized void endRepair(){
        canWork = false;
        notifyAll();
    }

    public synchronized Floor getFloor(){
        return floor;
    }
}