package com.company.Interfaces;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Opsymonroe on 30.09.2017.
 */
public interface Floor extends Serializable, Cloneable, Iterable<Space>, Comparable<Floor>{
    public int getSpaceCount();
    public double getFloorSummaryArea();
    public int getFloorSummaryRooms();
    public Space[] getSpacesArray();
    public Space getSpaceByNumber(int index);
    public void setSpaceByNumber(int index, Space space);
    public void insertSpaceByNumber(int index, Space space);
    public void deleteSpace(int index);
    public Space getBestSpace();
    public Object clone();
    public Iterator iterator();
    public int compareTo(Floor o);
}
