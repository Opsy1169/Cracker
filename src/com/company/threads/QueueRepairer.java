package com.company.threads;

import com.company.Interfaces.Floor;

/**
 * Created by Opsymonroe on 26.10.2017.
 */
public class QueueRepairer implements Runnable{
    private QueueSemaphore semaphore;
    private Floor floor;

    public QueueRepairer(QueueSemaphore semaphore, Floor floor){
        this.floor = floor;
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        repair();
    }

    private void repair(){
        try {
            //int indeloor().getSpaceCount();
            for (int i = 0; i < floor.getSpaceCount(); ++i) {
                semaphore.acquire(this);
                System.out.printf("Repairing room number %d with total area %f square meters\n", i, floor.getSpaceByNumber(i).getArea());
                semaphore.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
