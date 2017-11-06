package com.company.trash;

import com.company.trash.Semaphore;

/**
 * Created by Opsymonroe on 25.10.2017.
 */
public class SequentalRepairer implements Runnable {
    //Floor floor;
    Semaphore semaphore;

    public SequentalRepairer( Semaphore semaphore){
        //this.floor = floor;
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        repair();
    }

    private void repair(){
        try {
            int index = semaphore.getFloor().getSpaceCount();
            for (int i = 0; i < index; ++i) {
                semaphore.beginRepair();
                System.out.printf("Repairing room number %d with total area %f square meters\n", i, semaphore.floor.getSpaceByNumber(i).getArea());
                semaphore.endRepair();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
