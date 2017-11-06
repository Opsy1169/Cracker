package com.company.trash;

import com.company.trash.Semaphore;

/**
 * Created by Opsymonroe on 25.10.2017.
 */
public class SequentalCleaner implements Runnable {
    //Floor floor;
    Semaphore semaphore;

    public SequentalCleaner( Semaphore semaphore){
        //this.floor = floor;
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        clean();
    }

    private void clean(){
        try {
            //Space[] spaces = floor.getSpacesArray();
            int index = semaphore.getFloor().getSpaceCount();
            for (int i = 0; i < index; ++i) {
                semaphore.beginClean();
                System.out.printf("Cleaning room number %d with total area %f square meters\n", i, semaphore.floor.getSpaceByNumber(i).getArea());
                semaphore.endClean();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
