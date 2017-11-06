package com.company.buildings.dwelling;

import com.company.Exceptions.FloorIndexOutOfBoundsException;
import com.company.Exceptions.SpaceIndexOutOfBoundsException;
import com.company.Interfaces.Floor;
import com.company.Interfaces.Space;
import com.company.Iterators.SpaceIterator;

import java.util.Iterator;

/**
 * Created by Opsymonroe on 21.09.2017.
 */
public class DwellingFloor implements com.company.Interfaces.Floor {

    private Space[] flats;

    public DwellingFloor(int count) {
        if (count < 1)
            throw new FloorIndexOutOfBoundsException("Cant create such floor");
        flats = new Flat[count];
        for (int i = 0; i < count; ++i) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Space[] flatArray) {
        if (flatArray.length == 0)
            throw new SpaceIndexOutOfBoundsException("Cant create such floor");
        flats = flatArray;
    }

    public int getSpaceCount() {
        return flats.length;
    }

    public double getFloorSummaryArea() {
        double area = 0;
        for (Space flat : flats) {
            area += flat.getArea();
        }
        return area;
    }

    public int getFloorSummaryRooms() {
        int rooms = 0;
        for (Space flat : flats) {
            rooms += flat.getRooms();
        }
        return rooms;
    }

    public Space[] getSpacesArray() {
        return flats;
    }

    public Space getSpaceByNumber(int number) {
        if (number < 0 || number >= flats.length)
            throw new SpaceIndexOutOfBoundsException("DwellingFloor:getSpaceByNumber -- Number is out of flats range");
        return flats[number];
    }

    public void setSpaceByNumber(int number, Space flat) {
        if (number < 0 || number >= flats.length)
            throw new SpaceIndexOutOfBoundsException("DwellingFloor:setSpaceByNumber -- Number is out of flats range");
        flats[number] = new Flat(flat);
    }

    public void deleteSpace(int number) {
        if (number < 0 || number >= flats.length || flats.length == 1)
            throw new SpaceIndexOutOfBoundsException("DwellingFloor:deleteSpace -- Number is out of flats range");
        Space[] array = new Space[flats.length - 1];
        System.arraycopy(flats, 0, array, 0, number);
        System.arraycopy(flats, number + 1, array, number, array.length - number);
        flats = array;
    }

    public void insertSpaceByNumber(int number, Space flat) {
        if (number < 0 || number > flats.length)
            throw new SpaceIndexOutOfBoundsException("DwellingFloor:insertSpaceByNumber -- Number is out of flats range");
        Space[] array = new Space[flats.length + 1];
        if (number == 0) {
            array[0] = new Flat(flat);
            System.arraycopy(flats, 0, array, 1, flats.length);
            flats = array;
            return;
        }
        System.arraycopy(flats, 0, array, 0, number);
        array[number] = new Flat(flat);
        System.arraycopy(flats, number, array, number + 1, flats.length - number);
        flats = array;
        /*
        Flat[] array = new Flat[number+1];
        System.arraycopy(flats, 0, array, 0, flats.length);
        for(int i = flats.length; i < number; i++){
            array[i] = new Flat();
        }
        array[number] = flat;
        flats = array;*/
    }

    @Override
    public int hashCode() {
        int a = getSpaceCount();
        for (int i = 0; i < getSpaceCount(); i++) {
            a = a ^ getSpaceByNumber(i).hashCode();
        }
        return a;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof DwellingFloor))
            return false;
        DwellingFloor floor = (DwellingFloor) obj;
        if (floor.getSpaceCount() != this.getSpaceCount())
            return false;
        for (int i = 0; i < getSpaceCount(); i++) {
            if (!getSpaceByNumber(i).equals(floor.getSpaceByNumber(i)))
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String s = "DwellingFloor[";
        for (Space flat : flats) {
            s += flat.toString() + " ";
        }
        return s += "]";
    }

    public Space getBestSpace() {
        double max = 0;
        Space bestFlat = null;
        for (Space flat : flats) {
            double area = flat.getArea();
            if (area > max) {
                max = area;
                bestFlat = flat;
            }

        }
        return bestFlat;
    }

    @Override
    public Object clone() {
        DwellingFloor floor = null;
        Space[] spaces = new Space[getSpaceCount()];
        for (int i = 0; i < getSpaceCount(); ++i) {
            spaces[i] = (Space) getSpaceByNumber(i).clone();
        }
        floor = new DwellingFloor(spaces);
        return floor;
    }

    @Override
    public Iterator<Space> iterator() {
        return new SpaceIterator(this);
    }

    @Override
    public int compareTo(Floor o) {
        if(getSpaceCount() > o.getSpaceCount())
            return 1;
        if(getSpaceCount() == o.getSpaceCount())
            return 0;
        return -1;
    }


}
