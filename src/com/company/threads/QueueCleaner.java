package com.company.threads;

import com.company.Interfaces.Floor;

/**
 * Created by Opsymonroe on 26.10.2017.
 */
public class QueueCleaner implements Runnable{

    private QueueSemaphore semaphore;
    private Floor floor;


    public QueueCleaner( QueueSemaphore semaphore, Floor floor){
        this.floor = floor;
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        clean();
    }

    private void clean(){
        try {
            //Space[] spaces = floor.getSpacesArray();
            //int index = semaphore.getFloor().getSpaceCount();
            for (int i = 0; i < floor.getSpaceCount(); ++i) {
                semaphore.acquire(this);
                System.out.printf("Cleaning room number %d with total area %f square meters\n", i, floor.getSpaceByNumber(i).getArea());
                semaphore.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
