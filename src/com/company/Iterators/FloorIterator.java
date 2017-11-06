package com.company.Iterators;

import com.company.Interfaces.Building;
import com.company.Interfaces.Floor;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Opsymonroe on 19.10.2017.
 */
public class FloorIterator implements Iterator<Floor> {

    private Building building;
    private int index = -1;
    public FloorIterator(Building building){
        this.building = building;
    }
    @Override
    public boolean hasNext() {
        if(index+1 < building.getFloorCount())
            return true;
        return false;
    }

    @Override
    public Floor next() {
        if(hasNext()){
            return building.getFloorByNumber(++index);
        }
        else throw new NoSuchElementException("");
    }
    @Override
    public void remove(){throw  new UnsupportedOperationException("");}
}
