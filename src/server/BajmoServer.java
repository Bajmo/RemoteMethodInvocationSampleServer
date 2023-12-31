/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dao.IDao;
import entities.Classroom;
import entities.Machine;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ClassroomService;
import service.MachineService;

public class BajmoServer {

    public static void main(String[] args) {
        try {
            IDao<Machine> daoMachines = new MachineService();
            IDao<Classroom> daoClassrooms = new ClassroomService();
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://localhost:1099/machines", daoMachines);
            Naming.bind("rmi://localhost:1099/classrooms", daoClassrooms);
            System.out.println("Waiting for clients...");
        } catch (RemoteException | AlreadyBoundException | MalformedURLException e) {
            Logger.getLogger(BajmoServer.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
