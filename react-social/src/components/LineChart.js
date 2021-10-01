import React from 'react';
import { Line } from "react-chartjs-2";

export default function App() {
    return (
        <div className="App">
            <Line data={data}
                  height={50}
                  options={{ maintainAspectRatio: true }}/>
            <Line data={data1}
                  height={50}
                  options={{ maintainAspectRatio: true }}/>
            <Line data={data2}
                  height={50}
                  options={{ maintainAspectRatio: true }}/>
        </div>
    );
}

const data = {
    labels: ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],
    datasets: [
        {
            label: "Number of orders per month",
            data: [33, 69, 80, 111, 128, 90, 60, 40, 70, 94, 111, 89],
            fill: true,
            backgroundColor: "rgba(92,75,152,0.2)",
            borderColor: "rgba(92,75,152,1)"
        }
    ]
};

const data1 = {
    labels: ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],
    datasets: [
        {
            label: "Total Sales per month",
            data: [3300, 5500, 8700, 10100, 10800, 8000, 7670, 5890, 4990, 8400, 11100, 8900],
            fill: true,
            backgroundColor: "rgba(75,192,192,0.2)",
            borderColor: "rgba(75,192,192,1)"
        }
    ]
};

const data2 = {
    labels: ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],
    datasets: [
        {
            label: "Quantity of material wasted",
            data: [906, 793, 531, 799, 710, 608, 559, 510, 488, 430, 370, 350],
            fill: true,
            backgroundColor: "rgba(0,55,55,0.2)",
            borderColor: "rgba(0,55,55,1)"
        }
    ]
};



