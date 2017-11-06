package com.company.utilclasses;

import com.company.Interfaces.Floor;

import java.util.Comparator;

/**
 * Created by Opsymonroe on 19.10.2017.
 */
public class DownShiftFloorComparator implements Comparator<Floor> {
    @Override
    public int compare(Floor o1, Floor o2) {
        if(o1.getSpaceCount() > o2.getSpaceCount())
            return -1;
        if(o1.getSpaceCount() == o2.getSpaceCount())
            return 0;
        return 1;
    }
}
