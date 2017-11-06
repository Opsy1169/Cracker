package com.company.threads;

import com.company.Interfaces.Floor;
import com.company.Interfaces.Space;

/**
 * Created by Opsymonroe on 25.10.2017.
 */
public class Repairer extends Thread {
    Keeper floor;

    public Repairer(Keeper floor){
        this.floor = floor;
    }
    @Override
    public void run(){
        try{
            repair();
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    private void repair() throws InterruptedException{
        Space[] spaces = floor.getData().getSpacesArray();
        for (int i = 0; i < spaces.length; ++i) {
            System.out.printf("Repairing room number %d with total area %f square meters\n", i, spaces[i].getArea());
        }
        interrupt();
    }
    @Override
    public void interrupt(){
        super.interrupt();
        floor.finish();

    }

}
