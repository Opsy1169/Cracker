package com.company.Interfaces;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Opsymonroe on 30.09.2017.
 */
public interface Building extends Serializable, Cloneable, Iterable<Floor>{

    public int getFloorCount();
    public int getSpaceCount();
    public double getSummaryArea();
    public int getSummaryRooms();
    public Floor[] getFloorsArray();
    public Floor getFloorByNumber(int index);
    public void setFloorByNumber(int index, Floor floor);
    public Space getSpaceByNumber(int index);
    public void setSpaceByNumber(int index, Space space);
    public void insertSpaceByNumber(int index, Space space);
    public void deleteSpace(int index);
    public Space getBestSpace();
    public Space[] sort();
    public Object clone();
    public Iterator iterator();
}
