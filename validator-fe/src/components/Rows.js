import React, { Component } from 'react';

class Rows extends Component {
    render() {
        return (
            <tr style={{textAlign: "center"}}>
                <td style={{textAlign: "center"}} id="name">{this.props.customerName}</td>
                <td style={{textAlign: "center"}} id="country">{this.props.country}</td>
                <td style={{textAlign: "center"}} id="code">{this.props.code}</td>
                <td style={{textAlign: "center"}} id="phone">{this.props.phone}</td>
                <td style={{textAlign: "center"}} id="state">{this.props.state}</td>
            </tr>
        );
    }
}

export default Rows;
