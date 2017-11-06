package com.company.buildings.office;

import com.company.Exceptions.InvalidRoomsCountException;
import com.company.Exceptions.InvalidSpaceAreaException;
import com.company.Interfaces.Space;

/**
 * Created by Opsymonroe on 29.09.2017.
 */
public class Office implements Space{
    private static final int officeRooms = 1;
    private static final double officeArea = 250;

    private int rooms;
    private double area;

    @Override
    public String toString() {
        return "Office{" +
                "rooms=" + rooms +
                ", area=" + area +
                '}';
    }

    public Office (Space office){
        this.rooms = office.getRooms();
        this.area = office.getArea();
    }

    public Office(){
        this.rooms = officeRooms;
        this.area = officeArea;
    }

    @Override
    public int hashCode() {
        int areaHash = new Double(getArea()).hashCode();
        return getRooms()^areaHash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(!(obj instanceof Office))
            return false;
        Office office = (Office) obj;
        return (office.getRooms() == this.getRooms() && office.getArea() == this.getArea());
    }

    public Office(double area){
        if(area <= 0)
            throw new InvalidSpaceAreaException("Area cant be negative");
        this.rooms = officeRooms;
        this.area = area;
    }

    public int getRooms() {
        return rooms;
    }

    @Override
    public Object clone() {
        Object result = null;
        try{
            result = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int compareTo(Space o) {
        if(getArea() > o.getArea())
            return 1;
        if(getArea() == o.getArea())
            return 0;
        return -1;
    }

    public void setRooms(int rooms) {
        if(rooms <= 0)
            throw new InvalidRoomsCountException("Rooms count cant be negative");
        this.rooms = rooms;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        if(area <= 0)
            throw new InvalidSpaceAreaException("Area cant be negative");
        this.area = area;
    }

    public Office(int rooms, double area){
        if(area <= 0)
            throw new InvalidSpaceAreaException("Area cant be negative");
        if(rooms <= 0)
            throw new InvalidRoomsCountException("Rooms count cant be negative");
        this.rooms = rooms;
        this.area = area;
    }
}
