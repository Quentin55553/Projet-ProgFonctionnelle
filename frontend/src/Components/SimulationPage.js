import React, { useState } from "react";

function SimulationPage() {
    const [userId, setUserId] = useState("");
    const [portfolioName, setPortfolioName] = useState("");
    const [fromDate, setFromDate] = useState("");
    const [toDate, setToDate] = useState("");
    const [simulationResult, setSimulationResult] = useState(null);
    const [error, setError] = useState(null);

    const handleSimulate = () => {
        if (!userId || !portfolioName || !fromDate || !toDate) {
            setError("Tous les champs sont obligatoires");
            return;
        }
        setError(null);
        const url = `http://localhost:9000/simulate?userId=${userId}&portfolioName=${portfolioName}&from=${fromDate}&to=${toDate}`;
        fetch(url)
            .then((res) => res.json())
            .then((data) => {
                setSimulationResult(data);
            })
            .catch((err) => {
                console.error("Erreur simulation", err);
                setError("Erreur lors de la simulation");
            });
    };

    return (
        <div className="p-6">
            <h1 className="text-3xl font-bold mb-4">Simulation de Portefeuille</h1>
            <div className="max-w-md mx-auto">
                <input
                    type="number"
                    placeholder="User ID"
                    className="w-full p-2 mb-2 border rounded"
                    value={userId}
                    onChange={(e) => setUserId(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Nom du portefeuille"
                    className="w-full p-2 mb-2 border rounded"
                    value={portfolioName}
                    onChange={(e) => setPortfolioName(e.target.value)}
                />
                <input
                    type="date"
                    placeholder="Date de début (YYYY-MM-DD)"
                    className="w-full p-2 mb-2 border rounded"
                    value={fromDate}
                    onChange={(e) => setFromDate(e.target.value)}
                />
                <input
                    type="date"
                    placeholder="Date de fin (YYYY-MM-DD)"
                    className="w-full p-2 mb-2 border rounded"
                    value={toDate}
                    onChange={(e) => setToDate(e.target.value)}
                />
                <button
                    className="w-full p-2 bg-blue-500 text-white rounded mt-2"
                    onClick={handleSimulate}
                >
                    Simuler
                </button>
                {error && <p className="text-red-500 mt-2">{error}</p>}
            </div>

            {simulationResult && (
                <div className="max-w-6xl mx-auto mt-6 bg-white p-4 rounded shadow">
                    <h2 className="text-2xl font-bold mb-4">Résultats de Simulation</h2>
                    <h3 className="text-lg font-semibold mb-2">Historique agrégé</h3>
                    <table className="table-auto w-full border-collapse">
                        <thead className="bg-gray-200">
                        <tr>
                            <th className="border px-4 py-2">Date</th>
                            <th className="border px-4 py-2">Prix</th>
                        </tr>
                        </thead>
                        <tbody>
                        {simulationResult.historical.map((item, index) => (
                            <tr key={index} className="border-b">
                                <td className="border px-4 py-2">{item.date}</td>
                                <td className="border px-4 py-2">{item.price}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>

                    <div className="mt-4">
                        <h3 className="text-lg font-semibold">Prévisions par Régression Linéaire</h3>
                        <pre className="bg-gray-100 p-2 rounded">
              {JSON.stringify(simulationResult.predictedRegression, null, 2)}
            </pre>
                    </div>

                    <div className="mt-4">
                        <h3 className="text-lg font-semibold">Prévisions par Moyenne Mobile</h3>
                        <pre className="bg-gray-100 p-2 rounded">
              {JSON.stringify(simulationResult.predictedMovingAverage, null, 2)}
            </pre>
                    </div>
                </div>
            )}
        </div>
    );
}

export default SimulationPage;
