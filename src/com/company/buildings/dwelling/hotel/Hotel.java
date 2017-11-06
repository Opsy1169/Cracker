package com.company.buildings.dwelling.hotel;

import com.company.Interfaces.Floor;
import com.company.Interfaces.Space;
import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.dwelling.DwellingFloor;

/**
 * Created by Opsymonroe on 19.10.2017.
 */
public class Hotel extends Dwelling {
    public Hotel(int numberOfFloors, int[] numberOfFlatsOnFloor) {
        super(numberOfFloors, numberOfFlatsOnFloor);
    }

    public Hotel(Floor[] floors) {
        super(floors);
    }

    public int getHotelStars(){
        Floor[] floors = super.getFloorsArray();
        int max = 0;
        for(int i = 0; i < floors.length; ++i){
            if(floors[i] instanceof HotelFloor){
                HotelFloor floor = (HotelFloor) floors[i];
                if(floor.getStars() > max)
                    max = floor.getStars();
            }

        }
        return max;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Hotel))
            return false;
        Hotel dw = (Hotel) obj;
        if (dw.getSpaceCount() != this.getSpaceCount() || dw.getFloorCount() != this.getFloorCount())
            return false;
        for (int i = 0; i < getFloorCount(); i++) {
            if (!getFloorByNumber(i).equals(dw.getFloorByNumber(i)))
                return false;
        }

        return true;
    }


    @Override
    public String toString() {
        String s = "Hotel[" +
                getHotelStars() + " "
                + super.getFloorCount() + "\n";
        Floor[] floors = super.getFloorsArray();
        for (int i = 0; i < floors.length; i++) {
            s += floors[i].toString();
        }
        s+= "]";
        return s;
    }

    public Space getBestSpace(){
        double coeff = 0;
        Space space = null;
        Floor[] floors = super.getFloorsArray();
        for (int i = 0; i < getFloorCount(); i++) {
            if(floors[i] instanceof HotelFloor){
                HotelFloor floor = (HotelFloor) floors[i];
                Space inner = floor.getBestSpace();
                if(inner.getArea()*floor.starsCoeff[floor.getStars()] > coeff){
                    space = inner;
                    coeff = inner.getArea()*floor.starsCoeff[floor.getStars()];
                }
            }
        }
        return space;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
