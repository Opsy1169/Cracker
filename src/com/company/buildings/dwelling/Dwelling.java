package com.company.buildings.dwelling;

import com.company.Exceptions.FloorIndexOutOfBoundsException;
import com.company.Exceptions.SpaceIndexOutOfBoundsException;
import com.company.Interfaces.Floor;
import com.company.Interfaces.Space;
import com.company.Iterators.FloorIterator;

import java.util.Iterator;

/**
 * Created by Opsymonroe on 21.09.2017.
 */
public class Dwelling implements com.company.Interfaces.Building{

    private Floor[] floors;
    private  int[]  numberOfFlatsOnFloor;

    public Dwelling(int numberOfFloors, int[] numberOfFlatsOnFloor){
        if(numberOfFloors == 0)
            throw new FloorIndexOutOfBoundsException("Cant create such building");
        for(int i = 0; i < numberOfFloors; i++){
            floors[i] = new DwellingFloor(numberOfFlatsOnFloor[i]);
        }
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public Dwelling(Floor[] floors){
        if(floors.length == 0)
            throw new FloorIndexOutOfBoundsException("Cant create such building");
        this.floors = floors;
    }


    public int getSpaceCount(){
        int flats = 0;
        if(numberOfFlatsOnFloor != null){
            for(int i = 0; i < numberOfFlatsOnFloor.length; i++){
                flats += numberOfFlatsOnFloor[i];
            }
            return flats;
        }
        for (Floor floor: floors) {
            flats += floor.getSpaceCount();
        }
        return flats;
    }

    public double getSummaryArea(){
        double area = 0;
        for (Floor floor: floors) {
            area += floor.getFloorSummaryArea();
        }
        return area;
    }

    public  int getSummaryRooms(){
        int rooms = 0;
        for (Floor floor: floors) {
            rooms += floor.getFloorSummaryRooms();
        }
        return rooms;
    }

    public int getFloorCount(){
        return floors.length;
    }
    public Floor[] getFloorsArray(){
        return floors;
    }

    public Floor getFloorByNumber(int number){
        if(number < 0 || number >= floors.length)
            throw new FloorIndexOutOfBoundsException("Dwelling:getFloorByNumber -- Number is out of floors range");
        return floors[number];
    }

    public void setFloorByNumber(int number, Floor floor){
        if(number < 0 || number >= floors.length)
            throw new FloorIndexOutOfBoundsException("Dwelling:getFloorByNumber -- Number is out of floors range");
        floors[number] = new DwellingFloor(floor.getSpacesArray());
    }

    private int[] getFlatNumberOnTheFloorsBelow(int number){
        int num = 0;
        int floor = 0;
        while(number > num){
            num += floors[floor].getSpaceCount();
            ++floor;
        }
        floor--;
        num -= floors[floor].getSpaceCount();
        int[] a = {number-num, floor};
        return a;
    }

    public Space getSpaceByNumber(int number){
        if(number < 0 || number >= getSpaceCount())
            throw new SpaceIndexOutOfBoundsException("Dwelling:getFlatNumberOnTheFloorsBelow -- Number is out of flats range");
       int[] a = getFlatNumberOnTheFloorsBelow(number);
        return floors[a[1]].getSpaceByNumber(a[0]);
    }
    public void setSpaceByNumber(int number, Space flat){
        if(number < 0 || number >= getSpaceCount())
            throw new SpaceIndexOutOfBoundsException("Dwelling:getFlatNumberOnTheFloorsBelow -- Number is out of flats range");
        int[] a = getFlatNumberOnTheFloorsBelow(number);
        floors[a[1]].setSpaceByNumber(a[0], flat);
    }

    public void insertSpaceByNumber(int number, Space flat){
        if(number < 0 || number > getSpaceCount())
            throw new SpaceIndexOutOfBoundsException("Dwelling:getFlatNumberOnTheFloorsBelow -- Number is out of flats range");
        int[] a = getFlatNumberOnTheFloorsBelow(number);
        floors[a[1]].insertSpaceByNumber(a[0], flat);
    }

    public  void deleteSpace(int number){
        if(number < 0 || number >= getSpaceCount())
            throw new SpaceIndexOutOfBoundsException("Dwelling:getFlatNumberOnTheFloorsBelow -- Number is out of flats range");
        int[] a = getFlatNumberOnTheFloorsBelow(number);
        floors[a[1]].deleteSpace(a[0]);
    }

    @Override
    public int hashCode() {
        int a = getFloorCount();
        for (int i = 0; i < getFloorCount(); i++) {
            a = a ^ getFloorByNumber(i).hashCode();
        }
        return a;
    }

    public Space getBestSpace(){
        double max = 0;
        Space bestFlat = null;
        for (Floor floor: floors) {
            Space flat = floor.getBestSpace();
            double area = flat.getArea();
            if( area > max) {
                max = area;
                bestFlat =  flat;
            }
        }
        return bestFlat;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(!(obj instanceof Dwelling))
            return false;
        Dwelling dw = (Dwelling) obj;
        if(dw.getSpaceCount() != this.getSpaceCount() || dw.getFloorCount() != this.getFloorCount())
            return false;
        for (int i = 0; i < getFloorCount(); i++) {
            if(!getFloorByNumber(i).equals(dw.getFloorByNumber(i)))
                return false;
        }

        return true;
    }

    @Override
    public String toString(){
        String s = "Dwelling:" + "\n";
        for (Floor floor: floors) {
            s += floor.toString() + "\n";
        }
        return s;
    }

    public Space[] sort() {

        int length = getSpaceCount();
        Space[] arr = new Space[length];
        Space[] source;
        int index = 0;
        for (Floor floor: floors) {
            source = floor.getSpacesArray();
            System.arraycopy(source, 0, arr, index, source.length);
            index += source.length;
        }
        Space temp;
        int j;
        for(int i = 0; i < arr.length - 1; i++){
            if (arr[i].getArea() < arr[i + 1].getArea()) {
                temp = arr[i + 1];
                arr[i + 1] = arr[i];
                j = i;
                while (j > 0 && temp.getArea() > arr[j - 1].getArea()) {
                    arr[j] = arr[j - 1];
                    j--;
                }
                arr[j] = temp;
            }
        }
        return arr;
    }

    @Override
    public Object clone() {
        Dwelling dwelling = null;
        Floor[] floors = new Floor[getFloorCount()];
        for (int i = 0; i < getFloorCount(); ++i){
            floors[i] = (Floor)getFloorByNumber(i).clone();
        }
        dwelling = new Dwelling(floors);
        return dwelling;
    }

    @Override
    public Iterator<Floor> iterator() {
        return new FloorIterator(this);
    }
}
