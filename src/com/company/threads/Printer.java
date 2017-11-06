package com.company.threads;

import com.company.Interfaces.Space;
import com.company.Main;
import com.company.utilclasses.Buildings;

/**
 * Created by Opsymonroe on 25.10.2017.
 */
public class Printer extends Thread {
    String name;

    public Printer(String s){
        name = s;
    }

    @Override
    public void run() {
        try {
            print();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void print() throws InterruptedException {
        for (int i = 0; i < Buildings.synfloor.getSpaceCount(); ++i) {
            Buildings.synfloor.setSpaceByNumber(i, Buildings.synfloor.getSpaceByNumber(i));
            System.out.println("Thread #" + name + " " + Buildings.synfloor.getSpaceByNumber(i));
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();

    }
}
