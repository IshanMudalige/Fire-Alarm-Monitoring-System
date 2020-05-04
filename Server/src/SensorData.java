import okhttp3.*;
import java.io.IOException;
import java.util.*;

public class SensorData extends TimerTask {

    private final OkHttpClient httpClient = new OkHttpClient();
    int smokeLevel =0;
    int co2Level =0;
    Random r1 = new Random();
    Random r2 = new Random();
    private String sid;

    public SensorData(String sid){
        this.sid = sid;
    }

    //generate random number between 1-10 and update
    @Override
    public void run() {

        smokeLevel = r1.nextInt(10);
        co2Level = r2.nextInt(10);

        try {
            updateSensor(getSid(),smokeLevel,co2Level);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //--------------- update sensor data smoke level and co2 level------------------------------------------------------
    public void updateSensor(String sid,int smoke,int co2) throws Exception {

        RequestBody formBody = new FormBody.Builder()
                .add("sid", sid)
                .add("smokeLevel", String.valueOf(smoke))
                .add("co2Level", String.valueOf(co2))
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/updateData")
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

    public String getSid(){
        return this.sid;
    }



}
