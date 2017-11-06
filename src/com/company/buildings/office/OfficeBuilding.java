package com.company.buildings.office;

import com.company.Exceptions.FloorIndexOutOfBoundsException;
import com.company.Exceptions.SpaceIndexOutOfBoundsException;
import com.company.Interfaces.Floor;
import com.company.Interfaces.Space;
import com.company.Iterators.FloorIterator;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Opsymonroe on 30.09.2017.
 */
public class OfficeBuilding implements com.company.Interfaces.Building{
    private class OfficeBuildingNode implements Serializable{
        private OfficeBuildingNode next;
        private OfficeBuildingNode prev;
        private Floor officeFloor;




        public OfficeBuildingNode getPrev() {
            return prev;
        }

        public void setPrev(OfficeBuildingNode prev) {
            this.prev = prev;
        }

        public void setOfficeFloor(Floor officeFloor) {
            this.officeFloor = officeFloor;
        }

        public Floor getOfficeFloor() {
            return officeFloor;
        }

        public OfficeBuildingNode getNext() {

            return next;
        }

        public void setNext(OfficeBuildingNode next) {

            this.next = next;
        }

        public OfficeBuildingNode(OfficeBuildingNode next, Floor officeFloor, OfficeBuildingNode prev){
            this.next = next;
            this.prev = prev;
            this.officeFloor = officeFloor;
        }
    }


    private OfficeBuildingNode head;
    private int length;

    public OfficeBuilding(int floors, int[] officesOnEachFloor) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException{
        if( floors < 1)
            throw new FloorIndexOutOfBoundsException("Cant create such building");
        for(int i = 0; i < officesOnEachFloor.length; ++i){
            if(officesOnEachFloor[i] < 1)
                throw new SpaceIndexOutOfBoundsException("Cant create such building");
        }
        OfficeFloor floor = new OfficeFloor(officesOnEachFloor[0]);
        head = new OfficeBuildingNode(null, floor, null);
        head.setPrev(head);
        head.setNext(head);
        length = 1;
        for (int i = 1; i < floors; ++i){
            floor = new OfficeFloor(officesOnEachFloor[i]);
            addNodeToTail(new OfficeBuildingNode(null, floor, null));
            length++;
        }
    }

    @Override
    public int hashCode() {
        int a = getFloorCount();
        for (int i = 0; i < getFloorCount(); i++) {
            a = a ^ getFloorByNumber(i).hashCode();
        }
        return a;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(!(obj instanceof OfficeBuilding))
            return false;
        OfficeBuilding dw = (OfficeBuilding) obj;
        if(dw.getSpaceCount() != this.getSpaceCount() || dw.getFloorCount() != this.getFloorCount())
            return false;
        for (int i = 0; i < getFloorCount(); i++) {
            if(!getFloorByNumber(i).equals(dw.getFloorByNumber(i)))
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String s = "OfficeBuilding[" + "\n";
        for (int i = 0; i < length; i++) {
            s += getFloorByNumber(i).toString() + "\n";
        }
        return s;
    }

    public OfficeBuilding(Floor[] floors){
        head = new OfficeBuildingNode(null, floors[0], null);
        head.setNext(head);
        head.setPrev(head);
        length = 1;
        for (int i = 1; i < floors.length; i++) {
            addNodeToTail(new OfficeBuildingNode(null, floors[i], null));
            length++;
        }
    }

    public int getFloorCount(){
        return length;
    }

    public int getSpaceCount(){
        OfficeBuildingNode node = head;
        int offices = 0;
        for(int i = 0; i < length; ++i){
            offices += node.getOfficeFloor().getSpaceCount();
            node = node.getNext();
        }
        return offices;
    }

    public double getSummaryArea(){
        OfficeBuildingNode node = head;
        double area = 0;
        for (int i = 0; i < length; i++) {
            area += node.getOfficeFloor().getFloorSummaryArea();
            node = node.getNext();
        }
        return area;
    }

    public int getSummaryRooms(){
        OfficeBuildingNode node = head;
        int rooms = 0;
        for (int i = 0; i < length; i++) {
            rooms += node.getOfficeFloor().getFloorSummaryRooms();
            node = node.getNext();
        }
        return rooms;
    }

    public Floor[] getFloorsArray(){
        Floor[] floors = new Floor[length];
        OfficeBuildingNode node = head;
        for (int i = 0; i < length; i++) {
            floors[i] = node.getOfficeFloor();
            node = node.getNext();
        }
        return  floors;
    }

    private OfficeBuildingNode getNodeByIndex(int index) throws FloorIndexOutOfBoundsException{

        OfficeBuildingNode node  = head;
        for (int i = 0; i < index; ++i){
            node = node.getNext();
        }
        return node;
    }
    public Floor getFloorByNumber(int index){
        if(index < 0 || index > length)
            throw new FloorIndexOutOfBoundsException("Wrong floor");
        return getNodeByIndex(index).getOfficeFloor();
    }


    public void setFloorByNumber(int index, Floor floor){
        getNodeByIndex(index).setOfficeFloor(floor);
    }


    public Space getSpaceByNumber(int index){
        if(index < 0 || index >= getSpaceCount())
            throw new FloorIndexOutOfBoundsException("Wrong floor");
        int[] a = new int[2];
        a = getNumberAndFloor(index);
        Space office = getFloorByNumber(a[1]).getSpaceByNumber(a[0]);
        return office;
    }

    public void setSpaceByNumber(int index, Space office){
        int[] a;
        a = getNumberAndFloor(index);
        getFloorByNumber(a[1]).setSpaceByNumber(a[0], office);
    }

    public void insertSpaceByNumber(int index, Space office){
        if(index < 0 || index > getSpaceCount())
            throw new FloorIndexOutOfBoundsException("Wrong floor");
        if(index == getSpaceCount()){
            Floor floor = getFloorByNumber(length-1);
            floor.insertSpaceByNumber(floor.getSpaceCount(), office);
            return;
        }
        int[] a;
        a = getNumberAndFloor(index);
        getFloorByNumber(a[1]).insertSpaceByNumber(a[0], office);
    }

    public void deleteSpace(int index){
        if(index < 0 || index >= getSpaceCount())
            throw new FloorIndexOutOfBoundsException("Wrong floor");
        int[] a;
        a = getNumberAndFloor(index);
        getFloorByNumber(a[1]).deleteSpace(a[0]);
    }

    private void insertNodeByIndex(int index, OfficeBuildingNode node) throws FloorIndexOutOfBoundsException{

        OfficeBuildingNode prev = getNodeByIndex(index-1);
        OfficeBuildingNode next = prev.getNext();
        prev.setNext(node);
        node.setPrev(prev);
        node.setNext(next);
        next.setPrev(node);
    }

    private void deleteNodeByIndex(int index) throws FloorIndexOutOfBoundsException{

        OfficeBuildingNode prev = getNodeByIndex(index);
        OfficeBuildingNode next = prev.getNext().getNext();
        prev.setNext(next);
        next.setPrev(prev);
    }

    private void addNodeToTail(OfficeBuildingNode node){
        head.getPrev().setNext(node);
        node.setNext(head);
        node.setPrev(head.getPrev());
        head.setPrev(node);
    }

    private int[] getNumberAndFloor(int number) throws FloorIndexOutOfBoundsException{
        int num = 0;
        int floor = 0;
        OfficeBuildingNode node = head;
        while(number >= num){
            num += node.getOfficeFloor().getSpaceCount();
            node = node.getNext();
            ++floor;
        }
        floor--;
        num -= node.getPrev().getOfficeFloor().getSpaceCount();
        int[] a = {number-num, floor};
        return a;
    }

    public Space getBestSpace(){
        Floor[] floors = getFloorsArray();
        double max = 0;
        double area = 0;
        Space office = null;
        for (Floor floor : floors) {
            area = floor.getBestSpace().getArea();
            if(area > max){
                max = area;
                office = floor.getBestSpace();
            }

        }
        return office;
    }

    public Space[] sort(){
        Space[] spaces = new Space[getSpaceCount()];
        /*int length = getSpaceCount();
        Space[] arr = new Flat[length];
        Space[] source;
        int index = 0;
        for (Floor floor: floors) {
            source = floor.getSpacesArray();
            System.arraycopy(source, 0, arr, index, source.length);
            index += source.length;
        }*/
        OfficeBuildingNode node = head;
        Space[] source;
        int index = 0;
        for(int i = 0; i < length; ++i){
            source = node.getOfficeFloor().getSpacesArray();
            System.arraycopy(source, 0, spaces, index, source.length);
            index += source.length;
            node = node.getNext();
        }
        Space temp;
        int j;
        for(int i = 0; i < spaces.length - 1; i++){
            if (spaces[i].getArea() < spaces[i + 1].getArea()) {
                temp = spaces[i + 1];
                spaces[i + 1] = spaces[i];
                j = i;
                while (j > 0 && temp.getArea() > spaces[j - 1].getArea()) {
                    spaces[j] = spaces[j - 1];
                    j--;
                }
                spaces[j] = temp;
            }
        }
        return spaces;
    }

    @Override
    public Object clone() {
        OfficeBuilding building = null;
        Floor[] floors = new Floor[getFloorCount()];
        for (int i = 0; i < getFloorCount(); ++i){
            floors[i] = (Floor)getFloorByNumber(i).clone();
        }
        building = new OfficeBuilding(floors);
        return building;
    }

    @Override
    public Iterator<Floor> iterator() {
        return new FloorIterator(this);
    }
}
