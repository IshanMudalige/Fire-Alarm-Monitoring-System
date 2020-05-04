import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Client {

    Service service;

    public Client(){
        System.setProperty("java.security.policy", "file:allowall.policy");
        try {
            service =  (Service) Naming.lookup("//localhost/SensorService");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //adding new sensor
    public void add(String sid,String roomNum,String floorNum){
        try {
            service.add(sid,roomNum,floorNum);
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
        }
    }

    //update existing sensor details
    public void update(String sid,String roomNum,String floorNum){
        try {
            service.update(sid,roomNum,floorNum);
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
        }
    }

    //delete existing sensors
    public void delete(String sid){
        try {
            service.delete(sid);
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
        }
    }

    //get all sensors list
    public List<Sensor> getAll(){
        List<Sensor> list = new ArrayList();
        try {
            list = service.getAll();
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    //get number of connected sensors count
    public int getCount(){
        int count = 0;
        try {
            count = service.getCount();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return count;
    }

}
