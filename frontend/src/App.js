import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
   constructor() {
   super();
        this.state = {detail:[]};
     }
  componentDidMount(){
    	fetch(`http://localhost:8080/api/findServiceChargeAll`)
   		.then(result=>result.json())
      .then(detail=>this.setState({detail:detail}))
    }
  render() {
    return (
      <div className="App">
        <div className="App-header">
          <h2>ค่าบริการโทรศัพท์</h2>
        </div>
        <p className="App-intro">

           <table  className="App-Table">
           <thead>
             <th>
             เบอร์
             </th>
             <th>
             รวมเวลา(วินาที)
             </th>
             <th>
             คิดเป็นเงิน</th>

           </thead>
                         <tbody >
                          {this.state.detail.map((details, i) => <TableRow key = {i}
                                              detail= {details} />)}
                         </tbody>
                      </table>
        </p>
      </div>
    );
  }
}
class TableRow extends Component {
   render(){
   return(<tr>
                      <td>{this.props.detail.telNo}</td>
                      <td>{this.props.detail.totalTime}</td>
                      <td>{this.props.detail.serviceCharge}</td>

                   </tr>);
   }

}

export default App;
