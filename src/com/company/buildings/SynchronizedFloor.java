package com.company.buildings;

import com.company.Interfaces.Floor;
import com.company.Interfaces.Space;

import java.util.Iterator;

/**
 * Created by Opsymonroe on 25.10.2017.
 */
public class SynchronizedFloor implements Floor {

    Floor floor;

    public synchronized Floor getFloor() {
        return floor;
    }

    public synchronized void setFloor(Floor floor) {
        this.floor = floor;
    }

    public SynchronizedFloor(Floor floor){
        this.floor = floor;

    }
    @Override
    public synchronized int getSpaceCount() {
        return floor.getSpaceCount();
    }

    @Override
    public synchronized double getFloorSummaryArea() {
        return floor.getFloorSummaryArea();
    }

    @Override
    public synchronized int getFloorSummaryRooms() {
        return floor.getFloorSummaryRooms();
    }

    @Override
    public synchronized Space[] getSpacesArray() {
        return floor.getSpacesArray();
    }

    @Override
    public synchronized Space getSpaceByNumber(int index) {
        return floor.getSpaceByNumber(index);
    }

    @Override
    public synchronized void setSpaceByNumber(int index, Space space) {
        floor.setSpaceByNumber(index, space);
    }

    @Override
    public synchronized void insertSpaceByNumber(int index, Space space) {
        floor.insertSpaceByNumber(index, space);
    }

    @Override
    public synchronized void deleteSpace(int index) {
        floor.deleteSpace(index);
    }

    @Override
    public synchronized Space getBestSpace() {
        return floor.getBestSpace();
    }

    @Override
    public synchronized SynchronizedFloor clone() {
        return new SynchronizedFloor((Floor) floor.clone());
    }

    @Override
    public synchronized Iterator iterator() {
        return null;
    }

    @Override
    public synchronized int compareTo(Floor o) {
        return 0;
    }
}
