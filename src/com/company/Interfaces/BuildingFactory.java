package com.company.Interfaces;

/**
 * Created by Opsymonroe on 19.10.2017.
 */
public interface BuildingFactory {


    public Space createSpace(double area);
    public Space createSpace(int roomsCount, double area);
    public Floor createFloor(int spacesCount);
    public Floor createFloor(Space[] spaces);
    public Building createBuilding(int floorsCount, int[] spacesCounts);
    public Building createBuilding(Floor[] floors);
}
