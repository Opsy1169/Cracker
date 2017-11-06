package com.company.buildings.net.server.sequental;

import com.company.Exceptions.BuildingUnderArrestException;
import com.company.Factories.DwellingFactory;
import com.company.Factories.HotelFactory;
import com.company.Factories.OfficeFactory;
import com.company.Interfaces.Building;
import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.office.OfficeBuilding;
import com.company.utilclasses.Buildings;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

/**
 * Created by Opsymonroe on 04.11.2017.
 */
public class SerialServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        while(true){
            System.out.println("Waiting...");
            Socket socket = serverSocket.accept();
            System.out.println("connected");

//            BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//            BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
            //ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//            DataInputStream in = new DataInputStream(inputStream);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //in.mark(1000);
            try {
                String string = null;
                while (true) {
                    //string = objectInputStream.readUTF();

                    Building building = (Building) objectInputStream.readObject();
                    System.out.println(building.toString());
                    double cost = 0;
                    try {
                        cost = getPrice(building);
                    }catch (BuildingUnderArrestException exc){
                        System.out.println(exc.getMessage());
                        out.writeUTF(exc.getMessage());
                        out.flush();
                        continue;
                    }
                    out.writeUTF("Success. And building price is ");
                    out.writeDouble(cost);
                    out.flush();
                    System.out.println("done");

                }
            }catch (SocketException ex){
                System.out.println("client disconnected");
            }catch (IllegalArgumentException e){
                System.out.println(e.toString());
            }catch (EOFException ex){
                System.out.println("End of file was reached");
            } catch (ClassNotFoundException e) {
                System.out.println("Wrong class");
            }
            //inputStream.close();
            //outputStream.close();
            objectInputStream.close();
            //objectOutputStream.close();
            socket.close();
        }

    }

    public static double getPrice(Building building) throws BuildingUnderArrestException {
        Random random = new Random();
        if(random.nextInt()%10 == 0)
            throw  new BuildingUnderArrestException("Building is under arrest");
        if(building instanceof Dwelling){
            return building.getSummaryArea()* 1000;
        }
        if(building instanceof OfficeBuilding){
            return building.getSummaryArea()*1500;
        }
        else{
            return building.getSummaryArea()*2000;
        }
    }
}
