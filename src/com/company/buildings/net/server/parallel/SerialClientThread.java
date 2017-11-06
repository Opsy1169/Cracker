package com.company.buildings.net.server.parallel;

import com.company.Exceptions.BuildingUnderArrestException;
import com.company.Factories.DwellingFactory;
import com.company.Factories.HotelFactory;
import com.company.Factories.OfficeFactory;
import com.company.Interfaces.Building;
import com.company.utilclasses.Buildings;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

import static com.company.buildings.net.server.sequental.BinaryServer.getPrice;

/**
 * Created by Opsymonroe on 05.11.2017.
 */
public class SerialClientThread implements Runnable {

    private Socket socket = null;

    public SerialClientThread(Socket accept) {
        socket = accept;
    }

    @Override
    public void run() {
        try {
            System.out.println("Connected");
            //BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            //ObjectOutputStream out = new ObjectOutputStream(outputStream);
            try {
                String string = null;
                while (true) {
                    Building building = (Building) in.readObject();
                    System.out.println(building.toString());
                    double cost = 0;
                    try {
                        cost = getPrice(building);
                    } catch (BuildingUnderArrestException exc) {
                        System.out.println(exc.getMessage());
                        outputStream.writeUTF(exc.getMessage());
                        outputStream.flush();
                        continue;
                    }
                    outputStream.writeUTF("Success. And building price is ");
                    outputStream.writeDouble(cost);
                    outputStream.flush();
                }

            } catch (SocketException ex) {
                System.out.println("client disconnected");
            } catch (IllegalArgumentException e) {
                System.out.println(e.toString());
            } catch (EOFException ex) {
                System.out.println("End of file was reached");
            } catch (ClassNotFoundException e) {
                System.out.println("Wrong class");
            }
            //inputStream.close();
            outputStream.close();
            in.close();
            //out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

