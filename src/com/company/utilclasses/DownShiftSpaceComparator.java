package com.company.utilclasses;

import com.company.Interfaces.Space;

import java.util.Comparator;

/**
 * Created by Opsymonroe on 19.10.2017.
 */
public class DownShiftSpaceComparator implements Comparator<Space> {

    @Override
    public int compare(Space o1, Space o2) {
        if(o1.getArea() > o2.getArea())
            return -1;
        if(o1.getArea() == o2.getArea())
            return 0;
        return 1;
    }
}
