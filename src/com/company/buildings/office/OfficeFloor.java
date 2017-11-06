package com.company.buildings.office;

import com.company.Exceptions.FloorIndexOutOfBoundsException;
import com.company.Exceptions.SpaceIndexOutOfBoundsException;
import com.company.Interfaces.Floor;
import com.company.Interfaces.Space;
import com.company.Iterators.SpaceIterator;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Opsymonroe on 29.09.2017.
 */
public class OfficeFloor implements com.company.Interfaces.Floor{

    private class OfficeFloorNode implements Serializable{
        private Space office;
        private OfficeFloorNode next;

        public OfficeFloorNode( OfficeFloorNode next, Space office) {
            this.office = office;
            this.next = next;
        }

        public Space getOffice() {
            return office;
        }

        public void setOffice(Space office) {
            this.office = office;
        }

        public OfficeFloorNode getNext() {
            return next;
        }

        public void setNext(OfficeFloorNode next) {
            this.next = next;
        }
    }
    private OfficeFloorNode head;
    private int length;

    public OfficeFloor(int offices){
        if(offices < 1)
            throw new FloorIndexOutOfBoundsException("Cant create floor");
        head = new OfficeFloorNode(null, new Office());
        head.setNext(head);
        length = 1;
        OfficeFloorNode last = head;
        for (int i = 0; i < offices - 1; ++i){
            last.setNext(new OfficeFloorNode(head, new Office()));
            last = last.getNext();
            length++;
            //addNodeToTail(new OfficeFloorNode(null, new Office()));
        }
        head.getNext();
    }

    public OfficeFloor (Space[] offices){
        if(offices.length == 0)
            throw new SpaceIndexOutOfBoundsException("Cant create such floor");
        head = new OfficeFloorNode(null, offices[0]);
        length = 1;
        head.setNext(head);
        OfficeFloorNode last = head;
        for (int i = 1; i < offices.length; ++i){
            last.setNext(new OfficeFloorNode(head, offices[i]));
            last = last.getNext();
            length++;
            //addNodeToTail(new OfficeFloorNode(null, offices[i]));
        }
        head.getNext();
    }

    private OfficeFloorNode getNodeByIndex(int index){
//        if(index < 0 || index >= length)
//            throw  new SpaceIndexOutOfBoundsException("Wrong index");
        OfficeFloorNode node = head;
        for (int i = 0; i < index; ++i){
            node = node.getNext();
        }
        return node;
    }

    public Space getSpaceByNumber(int index){
        if(index < 0 || index >= length)
            throw  new SpaceIndexOutOfBoundsException("Wrong index");
        return getNodeByIndex(index).getOffice();
    }

    public void setSpaceByNumber(int index, Space office) {
        if(index < 0 || index >= length)
            throw  new SpaceIndexOutOfBoundsException("Wrong index");
        getNodeByIndex(index).setOffice(new Office(office));
    }

    @Override
    public int hashCode() {
        int a = getSpaceCount();
        for (int i = 0; i < getSpaceCount(); i++) {
            a = a ^ getSpaceByNumber(i).hashCode();
        }
        return a;
    }

    private void insertNodeByIndex(int index, OfficeFloorNode node) {
//        if(index < 0 || index > length)
//            throw  new SpaceIndexOutOfBoundsException("Wrong index");
        OfficeFloorNode prevNode = getNodeByIndex(index-1);
        OfficeFloorNode nextNode = prevNode.getNext();
        prevNode.setNext(node);
        node.setNext(nextNode);
        length++;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(!(obj instanceof OfficeFloor))
            return false;
        OfficeFloor floor = (OfficeFloor) obj;
        if(floor.getSpaceCount() != this.getSpaceCount())
            return false;
        for (int i = 0; i < getSpaceCount(); i++) {
            if(!getSpaceByNumber(i).equals(floor.getSpaceByNumber(i)))
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        OfficeFloorNode node = head;
        String s = "OfficeFloor[" ;
        for(int i = 0; i < length; i++){
            s += node.getOffice().toString() + " ";
            node = node.getNext();
        }
        return s += "]";
    }

    public void insertSpaceByNumber(int index, Space office){
        if(index < 0 || index > length)
            throw  new SpaceIndexOutOfBoundsException("Wrong index");
        OfficeFloorNode node = new OfficeFloorNode(null, office);
        insertNodeByIndex(index, node);
    }
    private void deleteNodeByIndex(int index){
//        if(index < 0 || index >= length)
//            throw  new SpaceIndexOutOfBoundsException("Wrong index");
        if(index == 0){
            OfficeFloorNode headnode = head.getNext();
            getNodeByIndex(length-1).setNext(headnode);
            head.setNext(null);
            head = headnode;
            length--;
            return;
        }
        OfficeFloorNode prevnode = getNodeByIndex(index-1);
        OfficeFloorNode nextNode = prevnode.getNext().getNext();
        prevnode.setNext(nextNode);
        length--;
    }

    public void deleteSpace(int index){
        if(index < 0 || index >= length)
            throw  new SpaceIndexOutOfBoundsException("Wrong index");
        deleteNodeByIndex(index);
    }

    private void addNodeToTail(OfficeFloorNode node){
        getNodeByIndex(length - 1).setNext(node);
        node.setNext(head);
        length++;
    }

    public int getSpaceCount(){
        return length;
    }

    public double getFloorSummaryArea(){
        OfficeFloorNode node = head;
        double area = 0;
        while(node.getNext() != head){
            area += node.getOffice().getArea();
            node = node.getNext();
        }
        area += node.getOffice().getArea();
        return area;
    }

    public int getFloorSummaryRooms(){
        OfficeFloorNode node = head;
        int rooms = 0;
        while(node.getNext() != head){
            rooms += node.getOffice().getRooms();
            node = node.getNext();
        }
        rooms += node.getOffice().getRooms();
        return rooms;
    }

    public Space[] getSpacesArray(){
        OfficeFloorNode node = head;
        Space[] offices = new Space[length];
        for (int i = 0; i < length; ++i) {
            offices[i] = node.getOffice();
            node = node.getNext();
        }
        return offices;
    }

    public Space getBestSpace(){
        OfficeFloorNode node = head;
        double area = 0;
        Space office = null;
        while(node.getNext() != head){
            if(node.getOffice().getArea() > area){
                area = node.getOffice().getArea();
                office = node.getOffice();
            }
            node = node.getNext();
        }
        if(node.getOffice().getArea() > area){
            office = node.getOffice();
        }
        return office;
    }

    @Override
    public Object clone() {
        OfficeFloor floor = null;
        Space[] spaces = new Space[getSpaceCount()];
        for (int i = 0; i < getSpaceCount(); ++i) {
            spaces[i] = (Space) getSpaceByNumber(i).clone();
        }
        floor = new OfficeFloor(spaces);
        return floor;
    }

    @Override
    public Iterator<Space> iterator() {
        return new SpaceIterator(this);
    }

    @Override
    public int compareTo(Floor o) {
        if(this.getSpaceCount() > o.getSpaceCount())
            return 1;
        if(this.getSpaceCount() == o.getSpaceCount())
            return 0;

        return -1;
    }
}
