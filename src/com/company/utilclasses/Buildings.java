package com.company.utilclasses;

import com.company.Factories.DwellingFactory;
import com.company.Interfaces.Building;
import com.company.Interfaces.BuildingFactory;
import com.company.Interfaces.Floor;
import com.company.Interfaces.Space;
import com.company.buildings.SynchronizedFloor;
import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.dwelling.DwellingFloor;
import com.company.buildings.dwelling.Flat;
import com.company.buildings.office.Office;

import java.io.*;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Opsymonroe on 06.10.2017.
 */
public class Buildings {
    private static BuildingFactory factory = new DwellingFactory();
    //public static SynchronizedFloor floor = new SynchronizedFloor()
    public static SynchronizedFloor synfloor = new SynchronizedFloor(new DwellingFloor(new Space[]{new Flat(1, 100), new Office(2, 110), new Flat(1, 120), new Flat(3, 200),
            new Office(2, 220), new Flat(1, 220), new Flat(5, 300), new Office(2, 330), new Flat(1, 320)}));
    public static Floor asynfloor = new DwellingFloor(new Space[]{new Flat(1, 100), new Office(2, 110), new Flat(1, 120), new Flat(3, 200),
            new Office(2, 220), new Flat(1, 220), new Flat(5, 300), new Office(2, 330), new Flat(1, 320),
            new Flat(3, 300), new Flat(1, 250), new Flat(5, 500), new Flat(10, 10), new Flat(10, 76)});

    public static void setBuildingFactory(BuildingFactory factory1){
        factory = factory1;
    }

    public static Space createFlat(double area){
        return factory.createSpace(area);
    }
    public static Space createFlat(int roomsCount, double area){
        return factory.createSpace(roomsCount, area);
    }
    public static Floor createFloor(int spaceCount){
        return factory.createFloor(spaceCount);
    }
    public static Floor createFloor(Space[] spaces){
        return factory.createFloor(spaces);
    }
    public static Building createBuilding(Floor[] floors){
        return factory.createBuilding(floors);
    }
    public static Building createBuilding(int floorsCount, int[] spaceCounts){
        return factory.createBuilding(floorsCount, spaceCounts);
    }

    public static void outputBuilding(Building building, OutputStream out) {
        DataOutputStream outputStream = new DataOutputStream(out);
        try {
            outputStream.writeInt(building.getFloorCount());
            for (int i = 0; i < building.getFloorCount(); ++i) {
                Floor floor = building.getFloorByNumber(i);
                int count = floor.getSpaceCount();
                outputStream.writeInt(count);
                for (int j = 0; j < count; ++j) {
                    Space space = floor.getSpaceByNumber(j);
                    outputStream.writeInt(space.getRooms());
                    outputStream.writeDouble(space.getArea());
                }
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Building inputBuilding(InputStream in) {
        DataInputStream inputStream = new DataInputStream(in);
        Building building = null;
        try {
            int floorsCount = inputStream.readInt();
            Floor[] floors = new Floor[floorsCount];
            for (int i = 0; i < floorsCount; ++i) {
                int spacesCount = inputStream.readInt();
                Space[] spaces = new Space[spacesCount];
                Space space = createFlat(100);
                for (int j = 0; j < spacesCount; ++j) {
                    space.setRooms(inputStream.readInt());
                    space.setArea(inputStream.readDouble());
                    spaces[j] = createFlat(space.getRooms(), space.getArea());
                }
                Floor floor = createFloor(spaces);
                floors[i] = floor;
            }
            building = createBuilding(floors);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return building;
    }

    //    public static void writeBuilding(Building building, Writer writer){
//        String s = building.getFloorCount() + " ";
//        for (int i = 0; i < building.getFloorCount(); ++i){
//            Floor floor = building.getFloorByNumber(i);
//            s += floor.getSpaceCount() + " ";
//            for(int j  = 0; j < floor.getSpaceCount(); ++j){
//                Space space = floor.getSpaceByNumber(j);
//                s += space.getRooms() + " " + space.getArea() + " ";
//            }
//        }
//        try {
//            writer.write(s);
//            writer.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
    public static void writeBuilding(Building building, Writer writer) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(building.getFloorCount() + " ");
        for (int i = 0; i < building.getFloorCount(); ++i) {
            Floor floor = building.getFloorByNumber(i);
            buffer.append(floor.getSpaceCount() + " ");
            for (int j = 0; j < floor.getSpaceCount(); ++j) {
                Space space = floor.getSpaceByNumber(j);
                buffer.append(space.getRooms() + " " + space.getArea() + " ");
            }
        }
        try {
            writer.write(buffer.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Building readBuilding(Reader reader) {
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        Building building = null;
        try {
            //int floorsCount = Integer.parseInt(tokenizer.nextToken());
            tokenizer.nextToken();
            int floorsCount = (int) tokenizer.nval;
            Floor[] floors = new Floor[floorsCount];
            for (int i = 0; i < floorsCount; ++i) {
                tokenizer.nextToken();
                int spaceCount = (int) tokenizer.nval;
                Space[] spaces = new Space[spaceCount];
                Space space = createFlat(100);
                for (int j = 0; j < spaceCount; ++j) {
                    tokenizer.nextToken();
                    space.setRooms((int) tokenizer.nval);
                    tokenizer.nextToken();
                    space.setArea(tokenizer.nval);
                    spaces[j] = createFlat(space.getRooms(), space.getArea());
                }
                floors[i] = createFloor(spaces);
            }
            building = createBuilding(floors);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return building;
    }


    public static Building readBuilding(String s) {
        Building building = null;
        try {
            //int floorsCount = Integer.parseInt(tokenizer.nextToken());
            int k = -1;
            String[] data = s.split(" ");
            int floorsCount = Integer.parseInt(data[++k]);
            Floor[] floors = new Floor[floorsCount];
            for (int i = 0; i < floorsCount; ++i) {
                int spaceCount = Integer.parseInt(data[++k]);//()int) tokenizer.nval;
                Space[] spaces = new Space[spaceCount];
                Space space = createFlat(100);
                for (int j = 0; j < spaceCount; ++j) {
                    space.setRooms(Integer.parseInt(data[++k]));
                    space.setArea(Double.parseDouble(data[++k]));
                    spaces[j] = createFlat(space.getRooms(), space.getArea());
                }
                floors[i] = createFloor(spaces);
            }
            building = createBuilding(floors);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return building;
    }


    public static void writeBuildingFormat(Building building, Writer out) {
        PrintWriter writer = new PrintWriter(out);
        writer.printf("%d", building.getFloorCount() + " ");
        for (int i = 0; i < building.getFloorCount(); ++i) {
            Floor floor = building.getFloorByNumber(i);
            writer.printf("%d", floor.getSpaceCount() + " ");
            for (int j = 0; j < floor.getSpaceCount(); ++j) {
                Space space = floor.getSpaceByNumber(j);
                writer.printf("%d %f", space.getRooms(), space.getArea() + " ");
            }
        }

        writer.flush();
    }

    public static Building readBuilding(Scanner scanner) {
        int floorsQuan = scanner.nextInt();
        Floor[] floors = new Floor[floorsQuan];
        for (int i = 0; i < floorsQuan; i++) {
            int spacesQuan = scanner.nextInt();
            Space[] spaces = new Space[spacesQuan];
            for (int j = 0; j < spacesQuan; j++) {
                int rooms = scanner.nextInt();
                double area = Double.parseDouble(scanner.next());
                spaces[j] = createFlat(rooms, area);
            }
            floors[i] = createFloor(spaces);
        }
        return createBuilding(floors);
    }

    public static <T extends Comparable<T>> T[] sort(T[] objects) {
        for (int i = 0; i < objects.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < objects.length; j++) {
                if (objects[j].compareTo(objects[minIndex]) < 0)
                    minIndex = j;
            }
            T swapBuf = objects[i];
            objects[i] = objects[minIndex];
            objects[minIndex] = swapBuf;
        }
        return objects;
    }

    public static <T> T[] sort(T[] objects, Comparator<T> comparator){
        for (int i = 0; i < objects.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < objects.length; j++) {
                if (comparator.compare(objects[j], objects[minIndex]) < 0)
                    minIndex = j;
            }
            T swapBuf = objects[i];
            objects[i] = objects[minIndex];
            objects[minIndex] = swapBuf;
        }
        return objects;
    }

    public Floor synchronizedFloor(Floor floor){
        return new SynchronizedFloor(floor);
    }

}
