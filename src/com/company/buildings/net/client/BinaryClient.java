package com.company.buildings.net.client;

import com.company.Factories.DwellingFactory;
import com.company.Factories.HotelFactory;
import com.company.Factories.OfficeFactory;
import com.company.Interfaces.Building;
import com.company.buildings.dwelling.Dwelling;
import com.company.utilclasses.Buildings;

import java.io.*;
import java.net.Socket;

/**
 * Created by Opsymonroe on 04.11.2017.
 */
public class BinaryClient {
    public static String[] str = {"asd", "first", "second", "third"};
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 7777);
        BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
        DataInputStream read = new DataInputStream(in);
        DataOutputStream write = new DataOutputStream(out);
        BufferedReader dataIn = new BufferedReader(new FileReader("D:/Users/Opsymonroe/IdeaProjects/NetCracker/fewBuildings.txt"));
        BufferedReader typeIn = new BufferedReader(new FileReader("D:/Users/Opsymonroe/IdeaProjects/NetCracker/BuildingsTypes"));
        //read.mark(1000);
        String BuildingData;
        String BuildingType;
        while(socket.isClosed()) {
        }
        while((BuildingData = dataIn.readLine()) != null && (BuildingType = typeIn.readLine()) != null) {
//            switch (BuildingType){
//                case "Dwelling":
//                    Buildings.setBuildingFactory(new DwellingFactory());
//                    break;
//                case "Office":
//                    Buildings.setBuildingFactory(new OfficeFactory());
//                    break;
//                case "Hotel":
//                    Buildings.setBuildingFactory(new HotelFactory());
//                    break;
//            }
            Building building = Buildings.readBuilding(BuildingData);
            write.writeUTF(BuildingType);
            Buildings.outputBuilding(building, write);
            String answer = read.readUTF();
            if(answer.equals("Building is under arrest")){
                System.out.println(answer);
                continue;
            }
            double cost = read.readDouble();
            System.out.println(answer + cost);

        }
        dataIn.close();
        typeIn.close();
        read.close();
        write.close();
        out.close();
        in.close();
        socket.close();

            //write.writeUTF(str[i]);
            //write.flush();
            //String string = read.readUTF();
            //System.out.println("Client: " + string);
            //in.reset();
        }
    }

