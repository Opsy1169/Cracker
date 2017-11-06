package com.company.threads;

import com.company.Interfaces.Space;

/**
 * Created by Opsymonroe on 25.10.2017.
 */
public class Cleaner extends Thread {

    Keeper floor;

    public Cleaner(Keeper floor) {
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
        Space[] spaces = floor.getData().getSpacesArray();
        for (int i = 0; i < spaces.length; ++i) {
            System.out.printf("Cleaning room number %d with total area %f square meters\n", i, spaces[i].getArea());
        }
        interrupt();
    }

    @Override
    public void interrupt() {
        super.interrupt();
        floor.finish();

    }

}

