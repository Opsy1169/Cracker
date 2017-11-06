package com.company.buildings.dwelling;

import com.company.Exceptions.InvalidRoomsCountException;
import com.company.Exceptions.InvalidSpaceAreaException;
import com.company.Interfaces.Space;

import java.io.Serializable;

/**
 * Created by Opsymonroe on 21.09.2017.
 */
public class Flat implements Space, Serializable{
    private static final int defaultNumberOfRooms = 2;
    private static final double defaultApartmentsArea = 50;

    private int rooms;
    private double area;


    @Override
    public int hashCode() {
        int areaHash = new Double(getArea()).hashCode();
        return getRooms()^areaHash;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "rooms=" + rooms +
                ", area=" + area +
                '}';
    }

    public Flat(Space flat){
        rooms = flat.getRooms();
        area = flat.getArea();
    }
    public  Flat(){
        area = defaultApartmentsArea;
        rooms = defaultNumberOfRooms;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(!(obj instanceof Flat))
            return false;
        Flat flat = (Flat) obj;
        return (flat.getRooms() == this.getRooms() && flat.getArea() == this.getArea());
    }

    public Flat(double area){
        if(area <= 0)
            throw new InvalidSpaceAreaException("Area cant be negative");
        this.area = area;
        rooms = defaultNumberOfRooms;
    }

    public Flat(int rooms, double area){
        if(area <= 0)
            throw new InvalidSpaceAreaException("Area cant be negative");
        if(rooms <= 0)
            throw new InvalidRoomsCountException("Rooms count cant be negative");
        this.rooms = rooms;
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

}
