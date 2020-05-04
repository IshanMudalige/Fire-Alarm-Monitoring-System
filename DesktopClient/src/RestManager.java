import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import java.io.IOException;
import java.util.List;

public class RestManager {

    private final OkHttpClient httpClient = new OkHttpClient();

    //------------------------------- register user -----------------------------------------------------------------------
    public boolean registerUser(String username,String password) throws Exception{
        RequestBody formBody = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/createUser")
                .addHeader("Content-Type", "application/json")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // Get response body
            //System.out.println(response.body().string());
            if(response.isSuccessful())
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //------------------------------- login user -----------------------------------------------------------------------
    public boolean loginUser(String username,String password) throws IOException {
        boolean st = false;
        RequestBody formBody = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/login")
                .addHeader("Content-Type", "application/json")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            st = Boolean.parseBoolean(response.body().string());
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return st;
    }

    //------------------------- get All sensors ----------------------------------------------------------------------
    public List<Sensor> getAll() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/getAll")
                .build();

        ObjectMapper mapper = new ObjectMapper();


        try (Response response = httpClient.newCall(request).execute()) {
            String lst = response.body().string();
            List<Sensor> pp3 = mapper.readValue(lst, new TypeReference<List<Sensor>>() {});
            return pp3;
        }
    }

}
