import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Timer;

public class Server extends UnicastRemoteObject implements Service {

    private final OkHttpClient httpClient = new OkHttpClient();
    private int sensorCount = 0;

    public Server() throws RemoteException {
        super();
    }

    public synchronized int getCount() throws RemoteException{
        return sensorCount;
    }

    @Override
    public void add(String sid,String roomNum,String floorNum) throws RemoteException {
        try {
            addSensor(sid,roomNum,floorNum);
            System.out.println("Sensor added");
            sensorCount++;
            Timer time = new Timer();
            SensorData st = new SensorData(sid);// start sensor dummy
            time.schedule(st, 0, 10000);// update sensor data every 10 seconds
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String sid, String roomNum, String floorNum) throws RemoteException {
        try {
            updateSensor(sid,roomNum,floorNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String sid) throws RemoteException {
        try {
            removeSensor(sid);
            System.out.println("Sensor deleted");
            if (sensorCount != 0)
                sensorCount--;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Sensor> getAll() throws RemoteException {
        List<Sensor> list = null;
        try {
            list = getSensors();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //-------------------------- main method ---------------------------------------------------------------------------
    public static void main(String[] args){

        System.setProperty("java.security.policy", "file:allowall.policy");

        try{

            Server svr = new Server();

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("SensorService", svr);

            System.out.println ("Service started....");

        }
        catch(RemoteException re){
            System.err.println(re.getMessage());
        }
        catch(AlreadyBoundException abe){
            System.err.println(abe.getMessage());
        }
    }

    //------------------------------- add sensor -----------------------------------------------------------------------
    public void addSensor(String sid,String roomNum,String floorNum) throws Exception{
        RequestBody formBody = new FormBody.Builder()
                .add("sid",sid)
                .add("roomNum",roomNum)
                .add("floorNum",floorNum)
                .add("smokeLevel", String.valueOf(0))
                .add("co2Level", String.valueOf(0))
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/create")
                .addHeader("Content-Type", "application/json")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // Get response body
            //System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //--------------------- delete sensor ------------------------------------------------------------------------------
    public void removeSensor(String sid) throws Exception {

        RequestBody formBody = new FormBody.Builder()
                .add("sid", sid)
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/delete")
                .addHeader("Content-Type", "application/json")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //---------------------------- update sensor -----------------------------------------------------------------------
    public void updateSensor(String sid,String fno,String rno) throws Exception {

        RequestBody formBody = new FormBody.Builder()
                .add("sid", sid)
                .add("floorNum", fno)
                .add("roomNum", rno)
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/update")
                .addHeader("Content-Type", "application/json")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //---------------------------------- get all -----------------------------------------------------------------------
    public List<Sensor> getSensors() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/getAll")
                .build();

        ObjectMapper mapper = new ObjectMapper();

        try (Response response = httpClient.newCall(request).execute()) {
            String lst = response.body().string();
            List<Sensor> list = mapper.readValue(lst, new TypeReference<List<Sensor>>() {});

            return list;
        }
    }

}
