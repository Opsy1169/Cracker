package com.company.Interfaces;

import java.io.Serializable;

/**
 * Created by Opsymonroe on 30.09.2017.
 */
public interface Space extends Serializable, Cloneable, Comparable<Space>{
    public void setArea(double area);
    public void setRooms(int rooms);
    public double getArea();
    public int getRooms();
    public Object clone();
    public int compareTo(Space o);
}
