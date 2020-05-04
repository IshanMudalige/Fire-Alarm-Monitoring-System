import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sensor {
    private IntegerProperty sid;
    private IntegerProperty floorNum;
    private IntegerProperty roomNum;
    private IntegerProperty smokeLevel;
    private IntegerProperty co2Level;

    Sensor(){
        this.sid = new SimpleIntegerProperty();
        this.floorNum = new SimpleIntegerProperty();
        this.roomNum = new SimpleIntegerProperty();
        this.smokeLevel = new SimpleIntegerProperty();
        this.co2Level = new SimpleIntegerProperty();

    }

    public int getSid() {
        return sid.get();
    }

    public IntegerProperty getSidProperty() {
        return sid;
    }


    public void setSid(int sid) {
        this.sid.set(sid);
    }
//-------------------------------------------
    public int getFloorNum() {
        return floorNum.get();
    }

    public IntegerProperty getFloorNumProperty() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum.set(floorNum);
    }
//---------------------------------------------------
    public int getRoomNum() {
        return roomNum.get();
    }

    public IntegerProperty getRoomNumProperty() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum.set(roomNum);
    }
//-----------------------------------
    public double getSmokeLevel() {
        return smokeLevel.get();
    }

    public IntegerProperty getSmokeLevelProperty() {
        return smokeLevel;
    }

    public void setSmokeLevel(int smokeLevel) {
        this.smokeLevel.set(smokeLevel);
    }
//---------------------------------
    public double getCo2Level() {
        return co2Level.get();
    }

    public IntegerProperty getCo2LevelProperty() {
        return co2Level;
    }

    public void setCo2Level(int co2Level) {
        this.co2Level.set(co2Level);
    }
}
