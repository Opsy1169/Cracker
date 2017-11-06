package com.company.Iterators;

import com.company.Interfaces.Floor;
import com.company.Interfaces.Space;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Opsymonroe on 19.10.2017.
 */
public class SpaceIterator implements Iterator<Space>{

    private Floor floor;
    private int index = -1;

    public SpaceIterator(Floor floor){
        this.floor = floor;
    }
    @Override
    public boolean hasNext() {
        return index + 1 < floor.getSpaceCount();
    }

    @Override
    public Space next() {
        if(hasNext())
            return floor.getSpaceByNumber(++index);
        else throw new NoSuchElementException("");

    }

    @Override
    public void remove(){
        throw new UnsupportedOperationException("");
    }
}
