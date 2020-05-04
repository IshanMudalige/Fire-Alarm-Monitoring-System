import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class HomeController implements Initializable {
    @FXML
    private TableView table;

    @FXML
    private TableColumn<Sensor, String> col_sid;

    @FXML
    private TableColumn<Sensor, String> col_floorNum;

    @FXML
    private TableColumn<Sensor, String> col_roomNum;

    @FXML
    private TableColumn<Sensor, String> col_smokeLevel;

    @FXML
    private TableColumn<Sensor, String> col_co2Level;

    @FXML
    private TextField txSid;

    @FXML
    private TextField txFloorNum;

    @FXML
    private TextField txRoomNum;

    @FXML
    private Text txCount;

    Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_sid.setCellValueFactory(cellData -> cellData.getValue().getSidProperty().asString());
        col_floorNum.setCellValueFactory(cellData -> cellData.getValue().getFloorNumProperty().asString());
        col_roomNum.setCellValueFactory(cellData -> cellData.getValue().getRoomNumProperty().asString());
        col_smokeLevel.setCellValueFactory(cellData -> cellData.getValue().getSmokeLevelProperty().asString());
        col_co2Level.setCellValueFactory(cellData -> cellData.getValue().getCo2LevelProperty().asString());

        client = new Client();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                populateTable();
            }
        }, 0, 15000);//update table every 15 seconds

        table.setRowFactory(tv -> {
            TableRow<Sensor> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    Sensor s = row.getItem();
                    txFloorNum.setText(String.valueOf(s.getFloorNum()));
                    txRoomNum.setText(String.valueOf(s.getRoomNum()));
                    txSid.setText(String.valueOf(s.getSid()));
                }
            });
            return row ;
        });

        //change row color if smoke level or co2 level exceed level 5
        table.setRowFactory(tv -> new TableRow<Sensor>() {
            @Override
            protected void updateItem(Sensor s, boolean empty) {
                super.updateItem(s, empty);
                if (s == null)
                    setStyle("");
                else if (s.getCo2Level() > 5 || s.getSmokeLevel()>5)
                    setStyle("-fx-background-color: #FF4658;");
                else
                    setStyle("");
            }
        });


    }

    //load sensor details into table
    private void populateTable(){
//        List<Sensor> sList = client.getAll();
//        ObservableList<Sensor> observableArrayList = FXCollections.observableArrayList(sList);
//        table.setItems(observableArrayList);

        RestManager restManager = new RestManager();
        List<Sensor> slist = null;
        try {
            slist = restManager.getAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Sensor> observableArrayList = FXCollections.observableArrayList(slist);
        table.setItems(observableArrayList);
        txCount.setText(String.valueOf(client.getCount()));
    }

    //remove sensor button
    @FXML
    public void removeSensor(javafx.event.ActionEvent actionEvent) throws Exception {
        Sensor s = (Sensor) table.getSelectionModel().getSelectedItem();
        client.delete(String.valueOf(s.getSid()));
        clear();
        populateTable();
    }


    //update sensor details button
    @FXML
    public void updateSensor(javafx.event.ActionEvent actionEvent) throws Exception {
        Sensor s = (Sensor) table.getSelectionModel().getSelectedItem();
        client.update(String.valueOf(s.getSid()),txFloorNum.getText(),txRoomNum.getText());
        //populateTable();
        clear();
    }

    //add sensor button
    @FXML
    public void addSensor(javafx.event.ActionEvent actionEvent) throws Exception {
        client.add(txSid.getText(),txRoomNum.getText(),txFloorNum.getText());
        clear();
        populateTable();

    }

    //clear text fields
    public void clear(){
        txFloorNum.setText("");
        txRoomNum.setText("");
        txSid.setText("");
    }





}
