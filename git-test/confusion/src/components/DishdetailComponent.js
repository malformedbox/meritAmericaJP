import React, { Component } from 'react';


class Dishdetail extends Component{
    render() {
        return (
            <div className="container">
            <div className="row">
                {this.props.menu}
            </div>
            <div className="row">
            {this.props.selectDish}
            </div>
            </div>
        );
    }
}
export default Dishdetail;