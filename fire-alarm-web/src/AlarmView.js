import React, {Component} from 'react';
import Table from 'react-bootstrap/Table';
import './App.css';

class AlarmView extends Component {
    state = {
        isloading : true,
        alarms : []
    }

    //---Asynchronous call to REST API
    async componentDidMount(){
        const url = "/getAll";
        const response = await fetch(url);
        const data = await response.json();
        this.setState({alarms :data, isloading:false});
        //console.log(data);
    }


    render() {

        //---Check the connection
        const {alarms,isloading} = this.state;
        if(isloading)
            return (<div>Loading.....</div>);

        this.state.alarms.map(a =>
            <div>
               if({a.smokeLevl} > 5 || {a.co2Level} > 5)
                    var st = {a.sid}
                    <h3>st</h3>
            </div>
        )

        //---Display the fetch data through the API
        return (
            <div>
                <br/>
               <h2 className="heading">...Fire Alarm System...</h2>
                <br/><br/>
                <Table striped bordered hover className="alarm">
                    <thead>
                    <tr>
                        <th>Floor Number</th>
                        <th>Room Number</th>
                        <th>Smoke Level</th>
                        <th>CO2 Level</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        alarms.map(alarm =>
                            <tr key = {alarm.sid} style= {{backgroundColor: (alarm.smokeLevel > 5 || alarm.co2Level > 5) ? "#FA8072" : "#90EE90"}}>
                                <td>{alarm.floorNum}</td>
                                <td>{alarm.roomNum}</td>
                                <td>{alarm.smokeLevel}</td>
                                <td>{alarm.co2Level}</td>
                            </tr>

                        )
                    }

                    </tbody>
                </Table>

            </div>


        );
    }
}

export default AlarmView;