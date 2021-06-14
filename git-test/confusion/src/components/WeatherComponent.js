import React, { Component } from 'react';
import { Jumbotron } from 'reactstrap';

class Weather extends Component {
    render(){
        return (
            <div class="container">
                <h1 class="heading">Simple Weather App</h1>
                <form>
                    <input type="text" placeholder="Search for a city" autofocus/>
                    <button type="submit">SUBMIT</button>
                    <span class="msg"></span>
                </form>
            </div>
        );
    }
}

export default Weather;