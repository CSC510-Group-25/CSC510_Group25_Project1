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
    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun"],
    datasets: [
        {
            label: "First dataset",
            data: [33, 53, 85, 41, 44, 65],
            fill: true,
            backgroundColor: "rgba(75,192,192,0.2)",
            borderColor: "rgba(75,192,192,1)"
        }
    ]
};

const data1 = {
    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun"],
    datasets: [
        {
            label: "First dataset",
            data: [33, 53, 85, 41, 44, 65],
            fill: true,
            backgroundColor: "rgba(75,192,192,0.2)",
            borderColor: "rgba(75,192,192,1)"
        }
    ]
};

const data2 = {
    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun"],
    datasets: [
        {
            label: "First dataset",
            data: [33, 53, 85, 41, 44, 65],
            fill: true,
            backgroundColor: "rgba(75,192,192,0.2)",
            borderColor: "rgba(75,192,192,1)"
        }
    ]
};



