package com.company;

import com.company.Factories.HotelFactory;
import com.company.Factories.OfficeFactory;
import com.company.Interfaces.Building;
import com.company.Interfaces.Floor;
import com.company.Interfaces.Space;
import com.company.buildings.SynchronizedFloor;
import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.dwelling.DwellingFloor;
import com.company.buildings.dwelling.Flat;
import com.company.buildings.dwelling.hotel.Hotel;
import com.company.buildings.dwelling.hotel.HotelFloor;
import com.company.buildings.office.Office;
import com.company.buildings.office.OfficeBuilding;
import com.company.buildings.office.OfficeFloor;
import com.company.threads.*;
import com.company.utilclasses.Buildings;
import com.company.utilclasses.DownShiftFloorComparator;
import com.company.utilclasses.DownShiftSpaceComparator;

import java.io.*;

public class Main {
    static int[] flats = {1, 2, 3, 4};

    public static void main(String[] args) throws IOException {
        Space[] spaces1 = {new Flat(1, 100), new Office(2, 110), new Flat(1, 120)};
        Space[] spaces2 = {new Flat(3, 200), new Office(2, 220), new Flat(1, 220)};
        Space[] spaces3 = {new Flat(5, 300), new Office(2, 330), new Flat(1, 320)};
        DwellingFloor livingFloor = new DwellingFloor(spaces1);
        OfficeFloor officeFloor = new OfficeFloor(spaces2);
        HotelFloor hotelFloor1 = new HotelFloor(spaces1);
        HotelFloor hotelFloor2 = new HotelFloor(spaces2);
        DwellingFloor dwellingFloor = new DwellingFloor(spaces3);
        Hotel hotel = new Hotel(new Floor[]{hotelFloor1, hotelFloor2, dwellingFloor});
        Dwelling dwelling = new Dwelling(new Floor[]{livingFloor, dwellingFloor});
       // System.out.println(hotel);
        //System.out.println(hotel.getBestSpace());
//        for (Floor floor: hotel) {
//            for (Space space : floor) {
//                System.out.println(space);
//            }
//        }

//        for (Space space : hotel.getFloorByNumber(2)) {
//            System.out.println(space);
//        }

        Floor floor = new DwellingFloor(spaces3);
        HotelFloor hotelFloor = new HotelFloor(spaces3);

//        Thread threadForSync = new Printer("first");
//        Thread threadForSync2 = new Printer("second");
//        Thread threadForSync3 = new Printer("third");
//        threadForSync.start();
//        threadForSync2.start();
//        threadForSync3.start();
//        Keeper keeper = new Keeper();
//        keeper.putData(livingFloor);
//        Keeper keeper1 = new Keeper();
//        keeper1.putData(dwellingFloor);
        //Thread thread1 = new NewCleaner(livingFloor);
        //Thread thread2 = new NewRepairer(livingFloor);
        //Thread thread3 = new NewRepairer(dwellingFloor);
        //Thread thread4 = new NewCleaner(dwellingFloor);
        //thread4.start();
        //thread2.start();
        //thread3.start();
        //thread1.start();
        Space[] spaces = new Space[30];
        for(int i = 0; i < 30; i++){
            spaces[i] = new Flat();
        }
        Floor floorMulti = new DwellingFloor(spaces);
        QueueSemaphore sem = new QueueSemaphore();
        Thread runCleaner = new Thread(new QueueCleaner(sem, floorMulti));
        Thread runRepairer = new Thread(new QueueRepairer(sem, floorMulti));
        //runRepairer.start();
        //runCleaner.start();

//        Flat flat = new Flat(5, 30);
//        Office office = new Office(5, 30);
//        int a = flat.hashCode()^0;
//        System.out.println("---------------------------------------");
//        Space[] sortedSpaces = Buildings.sort(livingFloor.getSpacesArray());
//        for (Space s: sortedSpaces) {
//            System.out.println(s);
//        }
//        System.out.println("---------------------------------------");
//        sortedSpaces = Buildings.sort(livingFloor.getSpacesArray(), new DownShiftSpaceComparator());
//        for (Space s: sortedSpaces) {
//            System.out.println(s);
//        }
//        System.out.println("---------------------------------------");
//        Building officeBuilding = new OfficeBuilding(new Floor[]{livingFloor, officeFloor, floor});
//        Floor[] sortedFloors = Buildings.sort(officeBuilding.getFloorsArray());
//        System.out.println("-----------------------------------------------");
//        for (Floor f: sortedFloors) {
//            System.out.println(f);
//        }
//        System.out.println("--------------------------------------------------");
//        sortedFloors = Buildings.sort(officeBuilding.getFloorsArray(), new DownShiftFloorComparator());
//        for (Floor f: sortedFloors) {
//            System.out.println(f);
//        }
//        Building otherOfficeBuilding = new OfficeBuilding(new Floor[]{livingFloor, officeFloor, floor});
//        System.out.println(officeBuilding.equals(officeBuilding));
//        //officeBuilding.equals(otherOfficeBuilding);
//        System.out.println(officeBuilding.equals(otherOfficeBuilding));
//        System.out.println(officeBuilding);
//        System.out.println(officeBuilding.getBestSpace());
//        int count = officeBuilding.getSpaceCount();
//        officeBuilding.insertSpaceByNumber(count, new Flat(10, 100));
//        System.out.println(officeBuilding);
//        officeBuilding.insertSpaceByNumber(0, new Office(13, 130));
//        //officeBuilding.deleteSpace(count);
//        System.out.println(officeBuilding);
//        Space[] spaces = officeBuilding.sort();
//        for (int i = 0; i < spaces.length; ++i){
//            System.out.println(spaces[i]);
//        }
        try(/*BufferedWriter out = new BufferedWriter(new FileWriter("fewBuildings.txt"));*/
        BufferedReader in = new BufferedReader(new FileReader("fewBuildings.txt"))) {
            //Buildings.writeBuilding(hotel, out);
            //Buildings.writeBuilding(dwelling, out);
            String s;
            while((s = in.readLine()) != null){
                Building building = Buildings.readBuilding(s);
                System.out.println(building);
            }
//            System.out.println(in.markSupported());
//            Buildings.writeBuilding(hotel, out);
//            System.out.println("read------------------------");
//            in.mark(1000);
//            Building building = Buildings.readBuilding(in);
//            System.out.println(building);
//            in.reset();
//            Buildings.setBuildingFactory(new OfficeFactory());
//            building = Buildings.readBuilding(in);
//            System.out.println(building);
//            in.reset();
//            Buildings.setBuildingFactory(new HotelFactory());
//            building = Buildings.readBuilding(in);
//            System.out.println(building);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int number){
        int[] a = {1, 2, 3, 4, 5};
        int[] aCopy = new int[a.length-1];
        System.arraycopy(a, 0, aCopy, 0, number);
        System.arraycopy(a, number+1, aCopy, number, aCopy.length - number);
        for (int b: aCopy) {
            System.out.println(b);
        }
    }

    public static void addFlat(int number, int z){
        /*
        int[] array = new int[number+1];
        System.arraycopy(flats, 0, array, 0, flats.length);
        for(int i = flats.length; i < number; i++){
            array[i] = 0;
        }
        array[number] = z;
        flats = array;
        */
        int[] array = new int[flats.length+1];
        if(number == 0){
            array[0] = z;
            System.arraycopy(flats, 0, array, 1, flats.length);
            return;
        }
        System.arraycopy(flats, 0, array, 0, number);
        array[number] = z;
        System.arraycopy(flats, number, array, number+1, flats.length-number);
        flats = array;
        for (int b: flats) {
            System.out.println(b);
        }
    }

    public static void insertIntoSort(int[] arr) {
        int temp, j;
        for(int i = 0; i < arr.length - 1; i++){
            if (arr[i] > arr[i + 1]) {
                temp = arr[i + 1];
                arr[i + 1] = arr[i];
                j = i;
                while (j > 0 && temp < arr[j - 1]) {
                    arr[j] = arr[j - 1];
                    j--;
                }
                arr[j] = temp;
            }
        }
    }

}
