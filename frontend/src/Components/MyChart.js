import React from "react";
import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend
} from "recharts";

function MyChart({ data, symbol }) {
    return (
        <div style={{ backgroundColor: "#fff", padding: "1rem", borderRadius: "5px" }}>
            <h3>Historique de {symbol}</h3>
            <LineChart width={400} height={250} data={data}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="date" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Line type="monotone" dataKey="price" stroke="#8884d8" />
            </LineChart>
        </div>
    );
}

export default MyChart;
