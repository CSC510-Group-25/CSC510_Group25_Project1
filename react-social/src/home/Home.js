import React, { Component } from 'react';
import './Home.css';
import Typography from "@material-ui/core/Typography";

class Home extends Component {
    render() {
        return (
            <div className="home-container">
                <div className="container">
                    <div className="graf-bg-container">
                        <div className="graf-layout">
                            <div className="graf-circle"></div>
                            <div className="graf-circle"></div>
                            <div className="graf-circle"></div>
                            <div className="graf-circle"></div>
                            <div className="graf-circle"></div>
                            <div className="graf-circle"></div>
                            <div className="graf-circle"></div>
                            <div className="graf-circle"></div>
                            <div className="graf-circle"></div>
                            <div className="graf-circle"></div>
                            <div className="graf-circle"></div>
                        </div>
                    </div>
                    <br/>
                    <b className="home-subtitle">America loses up to 218 BILLION
                        dollars from wasted food</b>
                    <br/>
                    <b className="home-subtitle">Restaurants in the United
                        States are wasting 22 to 33 BILLION pounds of food a
                        year</b>
                    <b className="home-ref">[1]</b>
                    <br/>
                    <h2 className="home-subtitle">Let's change that!</h2>
                    <h1 className="home-title">86 No More: A Kitchen Intelligence System</h1>
                    <b className="home-ref">[1] Gunders, Dana. “Wasted: How America
                        is Losing Up to 40 Percent of Its Food from Farm to Fork
                        to Landfill.” Natural Resources Defense Council, 2017.
                        Retrieved September 27, 2021, from https://www.nrdc.org
                        /sites/default/files/wasted-2017-report.pdf</b>
                </div>
            </div>
        )
    }
}

export default Home;