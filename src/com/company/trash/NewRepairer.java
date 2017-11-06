package com.company.trash;

import com.company.Interfaces.Floor;
import com.company.Interfaces.Space;

/**
 * Created by Opsymonroe on 26.10.2017.
 */
public class NewRepairer extends Thread {


    Floor floor;

    public NewRepairer(Floor floor){
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
        synchronized (floor) {
            //Space[] spaces = floor.getData().getSpacesArray();
            for (int i = 0; i < floor.getSpaceCount(); ++i) {
                System.out.printf("Repairing room number %d with total area %f square meters\n", i, floor.getSpaceByNumber(i).getArea());
            }
            //interrupt();
        }
    }
    @Override
    public void interrupt(){
        super.interrupt();
        //notifyAll();

    }
}
