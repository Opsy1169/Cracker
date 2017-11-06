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
public class BinaryClientThread implements Runnable {

    private Socket socket = null;

    public BinaryClientThread(Socket accept) {
        socket = accept;
    }

    @Override
    public void run() {
        try {
            BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(inputStream);
            DataOutputStream out = new DataOutputStream(outputStream);
            try {
                String string = null;
                while (true) {
                    string = in.readUTF();
                    switch (string) {
                        case "Dwelling":
                            Buildings.setBuildingFactory(new DwellingFactory());
                            break;
                        case "Office":
                            Buildings.setBuildingFactory(new OfficeFactory());
                            break;
                        case "Hotel":
                            Buildings.setBuildingFactory(new HotelFactory());
                            break;
                        default:
                            throw new IllegalArgumentException("Wrongtype");
                    }
                    Building building = Buildings.inputBuilding(in);
                    System.out.println(building.toString());
                    double cost = 0;
                    try {
                        cost = getPrice(building);
                    } catch (BuildingUnderArrestException exc) {
                        System.out.println(exc.getMessage());
                        out.writeUTF(exc.getMessage());
                        out.flush();
                        continue;
                    }
                    out.writeUTF("Success. And building price is ");
                    out.writeDouble(cost);
                    out.flush();
                }

            } catch (SocketException ex) {
                System.out.println("client disconnected");
            } catch (IllegalArgumentException e) {
                System.out.println(e.toString());
            } catch (EOFException ex) {
                System.out.println("End of file was reached");
            }
            inputStream.close();
            outputStream.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

