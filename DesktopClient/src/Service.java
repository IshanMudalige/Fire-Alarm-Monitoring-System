import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Service extends Remote{
    void add(String sid,String roomNum,String floorNum) throws RemoteException;
    void update(String sid,String roomNum,String floorNum) throws RemoteException;
    void delete(String sid) throws RemoteException;
    List<Sensor> getAll() throws RemoteException;
    int getCount() throws RemoteException;
}