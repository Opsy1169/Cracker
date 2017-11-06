package com.company.utilclasses;


import com.company.Exceptions.InexchangeableFloorsException;
import com.company.Exceptions.InexchangeableSpacesException;
import com.company.Interfaces.Building;
import com.company.Interfaces.Floor;
import com.company.Interfaces.Space;

/**
 * Created by Opsymonroe on 06.10.2017.
 */
public class PlacementExchanger {

    public static boolean isExchangeable(Space first, Space second){
        if(first.getArea() == second.getArea() && first.getRooms() == second.getRooms()){
            return true;
        }
        return false;
    }

    public static boolean isExchangeable(Floor first, Floor second){
        if(first.getFloorSummaryArea() == second.getFloorSummaryArea() && first.getFloorSummaryRooms() == second.getFloorSummaryRooms()){
            return true;
        }
        return false;
    }

    public static void exchangeFloorRooms(Floor first, int index1, Floor second, int index2){
        Space space1 = first.getSpaceByNumber(index1);
        Space space2 = second.getSpaceByNumber(index2);
        if(isExchangeable(space1, space2)){
            first.setSpaceByNumber(index1, space2);
            second.setSpaceByNumber(index2, space1);
            return;
        }else{
            throw new InexchangeableSpacesException("Cant swap spaces");
        }
    }

    public  static void exchangeBuildingFloors(Building first, int index1, Building second, int index2){
        Floor floor1 = first.getFloorByNumber(index1);
        Floor floor2 = second.getFloorByNumber(index2);
        if(isExchangeable(floor1, floor2)){
            first.setFloorByNumber(index1, floor2);
            second.setFloorByNumber(index2, floor1);
            return;
        }else{
            throw new InexchangeableFloorsException("Cant swap floors");
        }
    }
}
