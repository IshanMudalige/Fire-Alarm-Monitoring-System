import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField txUname;

    @FXML
    private PasswordField txPwd;

    @FXML
    private Text txMsg;

    RestManager restManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        restManager = new RestManager();
    }

    //login button method
    @FXML
    public void login(javafx.event.ActionEvent actionEvent) throws Exception {
        boolean st = restManager.loginUser(txUname.getText(),txPwd.getText());

        if(st){
            changeScene("UI/HomeUI.fxml");
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }else{
            txMsg.setText("Login Failed");
            txMsg.setFill(Color.RED);
        }
    }

    //register button method
    @FXML
    public void register(javafx.event.ActionEvent actionEvent) throws Exception {
        boolean st = restManager.registerUser(txUname.getText(),txPwd.getText());
        if(st){
            txMsg.setText("Registration Success");
            txMsg.setFill(Color.GREEN);
            clear();
        }else{
            txMsg.setText("Registration Failed");
            txMsg.setFill(Color.RED);
        }

    }

    //change scene
    public void changeScene(String fxmlName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlName));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //clear text fields
    public void clear(){
        txPwd.setText("");
        txUname.setText("");
    }

}
