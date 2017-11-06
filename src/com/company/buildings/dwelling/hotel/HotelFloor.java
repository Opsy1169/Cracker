package com.company.buildings.dwelling.hotel;

import com.company.Interfaces.Space;
import com.company.buildings.dwelling.DwellingFloor;

/**
 * Created by Opsymonroe on 19.10.2017.
 */
public class HotelFloor extends DwellingFloor {
    private static final int defaultStars = 1;
    public static final double[] starsCoeff = {0.25, 0.5, 1, 1.25, 1.5};
    private int stars;


    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }


    public HotelFloor(int count) {
        super(count);
        stars = defaultStars;
    }

    public HotelFloor(Space[] flatArray) {
        super(flatArray);
        stars = defaultStars;
    }

    @Override
    public String toString() {
       String s =  "HotelFloor[" +
                "stars=" + stars +
                " spaces=" + super.getSpaceCount() + "; ";
        Space[] spaces = super.getSpacesArray();
        for (int i = 0; i < super.getSpaceCount(); i++) {
            s += spaces[i].toString();
        }
        s += "]\n";
        return s;
    }

    public Space getBestSpace(){
        Space space = null;
        double max = 0;
        for (int i = 0; i < super.getSpaceCount(); i++) {
            double coeff = getSpaceByNumber(i).getArea()*starsCoeff[getStars()-1];
            if(coeff > max)
                space = getSpaceByNumber(i);
                max = coeff;
        }
        return space;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof HotelFloor))
            return false;
        HotelFloor floor = (HotelFloor) obj;
        if (floor.getSpaceCount() != this.getSpaceCount() && floor.getStars() != this.getStars())
            return false;
        for (int i = 0; i < getSpaceCount(); i++) {
            if (!getSpaceByNumber(i).equals(floor.getSpaceByNumber(i)))
                return false;
        }

        return true;
    }

    public int hashCode() {
        return super.hashCode()^getStars();
    }
}
