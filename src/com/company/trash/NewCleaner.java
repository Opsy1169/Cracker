package com.company.trash;

import com.company.Interfaces.Floor;
import com.company.Interfaces.Space;

/**
 * Created by Opsymonroe on 26.10.2017.
 */
public class NewCleaner extends Thread {

    Floor floor;

    public NewCleaner(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        try {
            clean();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void clean() throws InterruptedException {
        synchronized (floor) {
            for (int i = 0; i < floor.getSpaceCount(); ++i) {
                System.out.printf("Cleaning room number %d with total area %f square meters\n", i, floor.getSpaceByNumber(i).getArea());
            }
            //interrupt();
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        //notifyAll();

    }
}
