import "../style.css";
import MyChart from "./MyChart";

import React, { useState, useEffect } from "react";
import {
    PieChart,
    Pie,
    Cell,
    Tooltip,
    BarChart,
    Bar,
    XAxis,
    YAxis,
    CartesianGrid,
    Legend,
} from "recharts";

const Card = ({ children }) => (
    <div className="p-4 bg-white shadow-md rounded-lg">{children}</div>
);

const CardContent = ({ children }) => <div className="p-2">{children}</div>;

const Button = ({ children, ...props }) => (
    <button className="p-2 bg-blue-500 text-white rounded" {...props}>
        {children}
    </button>
);

const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042", "#A28EFF"];

function Welcome() {
    // === ÉTATS ===
    const [user, setUser] = useState(null);
    const [portfolios, setPortfolios] = useState([]);
    const [selectedPortfolioName, setSelectedPortfolioName] = useState("");
    const [newPortfolioName, setNewPortfolioName] = useState("");
    const [newAsset, setNewAsset] = useState({ symbol: "", quantity: "" });
    const [prices, setPrices] = useState({}); // { BTC: 30000, ETH: 1800, AAPL: 170, ... }

    // Pour le symbole cliqué et l'historique
    const [selectedSymbol, setSelectedSymbol] = useState(null);
    const [historicalData, setHistoricalData] = useState([]);

    // === EFFETS ===
    // 1) Charger l'utilisateur et ses portefeuilles
    useEffect(() => {
        fetch("http://localhost:9000/userInfo", { method: "GET", credentials: "include" })
            .then((res) => res.json())
            .then((userData) => {
                if (userData.id) {
                    setUser(userData);
                    return fetch(`http://localhost:9000/portfolios/${userData.id}`);
                } else {
                    throw new Error("Utilisateur non connecté");
                }
            })
            .then((res) => res.json())
            .then((data) => {
                const flattened = data.flatMap((userPortfolio) =>
                    userPortfolio.portfolios ? Object.values(userPortfolio.portfolios) : []
                );
                setPortfolios(flattened);
            })
            .catch((error) => console.error("Erreur lors du chargement des données:", error));
    }, []);

    // 2) Charger l'historique quand selectedSymbol change
    useEffect(() => {
        if (!selectedSymbol) return;
        fetch(`http://localhost:9000/getHistoricalData?symbol=${selectedSymbol}`)
            .then((res) => res.json())
            .then((data) => {
                // data : [{ date, price }, ...]
                setHistoricalData(data);
            })
            .catch((err) => console.error("Erreur fetch historique", err));
    }, [selectedSymbol]);

    // 3) Charger les prix unitaires quand on change de portefeuille sélectionné
    const selectedPortfolio = portfolios.find((p) => p.name === selectedPortfolioName);

    useEffect(() => {
        if (!selectedPortfolio) return;
        const symbols = Object.keys(selectedPortfolio.assets);

        symbols.forEach((symbol) => {
            const url = `http://localhost:9000/getPrice?symbol=${symbol}`;
            fetch(url)
                .then((res) => res.json())
                .then((data) => {
                    if (data.currentPrice !== undefined) {
                        setPrices((prev) => ({ ...prev, [symbol]: data.currentPrice }));
                    }
                })
                .catch((err) => console.error("Erreur fetch price pour", symbol, err));
        });
    }, [selectedPortfolio]);

    // === FONCTIONS ===
    const handleSymbolClick = (symbol) => {
        setSelectedSymbol(symbol);
    };

    const handleCreatePortfolio = () => {
        if (!newPortfolioName) return;
        fetch("http://localhost:9000/createPortfolio", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ userId: user.id, portfolioName: newPortfolioName }),
        }).then(() => {
            setNewPortfolioName("");
            window.location.reload();
        });
    };

    const handleAddAsset = (portfolioName, symbol, quantity) => {
        if (!symbol || !quantity) return;
        fetch("http://localhost:9000/addAsset", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                userId: user.id,
                portfolioName,
                symbol,
                quantity: parseInt(quantity, 10),
            }),
        }).then(() => {
            setNewAsset({ symbol: "", quantity: "" });
            window.location.reload();
        });
    };

    const handleRemoveAsset = (portfolioName, symbol) => {
        fetch("http://localhost:9000/removeAsset", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                userId: user.id,
                portfolioName,
                symbol,
            }),
        }).then(() => {
            window.location.reload();
        });
    };

    // === Données pour les graphiques ===
    // PieChart
    const combinedAssets = {};
    portfolios.forEach((p) => {
        Object.entries(p.assets).forEach(([symbol, qty]) => {
            combinedAssets[symbol] = (combinedAssets[symbol] || 0) + qty;
        });
    });
    const pieData = Object.entries(combinedAssets).map(([symbol, qty]) => ({
        name: symbol,
        value: qty,
    }));

    // BarChart
    const allSymbols = new Set();
    portfolios.forEach((p) => {
        Object.keys(p.assets).forEach((symbol) => allSymbols.add(symbol));
    });
    const symbolArray = Array.from(allSymbols);

    const chartData = portfolios.map((p) => {
        const row = { name: p.name };
        symbolArray.forEach((symbol) => {
            row[symbol] = p.assets[symbol] || 0;
        });
        return row;
    });

    // === RENDU ===
    return (
        <div className="bg-blue-900 min-h-screen text-white p-6">
            {user ? (
                // On crée un layout à 2 colonnes :
                <div className="max-w-6xl mx-auto" style={{ display: "flex", gap: "2rem" }}>
                    {/* ===== Colonne de gauche : PieChart, BarChart, Gestion de portefeuilles ===== */}
                    <div style={{ flex: 2 }}>
                        <h1 className="text-3xl font-bold mb-4">Bienvenue, {user.username} 👋</h1>

                        {/* ====================== GRAPHIQUES ====================== */}
                        <div style={{ display: "flex", gap: "2rem", alignItems: "flex-start" }}>
                            {/* Graphique camembert */}
                            <Card>
                                <CardContent>
                                    <h2 className="text-lg font-semibold">Répartition des Actifs</h2>
                                    <PieChart width={300} height={300}>
                                        <Pie
                                            data={pieData}
                                            cx={150}
                                            cy={150}
                                            innerRadius={50}
                                            outerRadius={100}
                                            fill="#8884d8"
                                            paddingAngle={5}
                                            dataKey="value"
                                        >
                                            {pieData.map((entry, index) => (
                                                <Cell
                                                    key={`slice-${entry.name}`}
                                                    fill={COLORS[index % COLORS.length]}
                                                />
                                            ))}
                                        </Pie>
                                        <Tooltip />
                                    </PieChart>

                                    {/* Légende manuelle */}
                                    <div style={{ marginTop: "0.5rem", display: "flex", flexWrap: "wrap" }}>
                                        {pieData.map((entry, index) => (
                                            <div
                                                key={entry.name}
                                                style={{
                                                    display: "flex",
                                                    alignItems: "center",
                                                    marginRight: "1rem",
                                                    marginBottom: "0.5rem",
                                                }}
                                            >
                                                <div
                                                    style={{
                                                        width: "0.75rem",
                                                        height: "0.75rem",
                                                        backgroundColor: COLORS[index % COLORS.length],
                                                        marginRight: "0.25rem",
                                                    }}
                                                />
                                                <span>{entry.name}</span>
                                            </div>
                                        ))}
                                    </div>
                                </CardContent>
                            </Card>

                            {/* Graphique barres */}
                            <Card>
                                <CardContent>
                                    <h2 className="text-lg font-semibold">Comparaison des Portefeuilles</h2>
                                    <BarChart width={400} height={300} data={chartData}>
                                        <CartesianGrid strokeDasharray="3 3" />
                                        <XAxis dataKey="name" />
                                        <YAxis />
                                        <Tooltip />
                                        <Legend />
                                        {symbolArray.map((symbol, index) => (
                                            <Bar
                                                key={symbol}
                                                dataKey={symbol}
                                                stackId="a"
                                                fill={COLORS[index % COLORS.length]}
                                                name={symbol}
                                            />
                                        ))}
                                    </BarChart>
                                </CardContent>
                            </Card>
                        </div>

                        {/* ====================== GESTION DES PORTEFEUILLES ====================== */}
                        <div className="mt-6 p-6 bg-white rounded-lg shadow-md text-black">
                            <h2 className="text-lg font-bold mb-2">Gérer vos Portefeuilles</h2>
                            <div className="flex items-center space-x-2 mb-4">
                                <input
                                    className="p-2 border rounded"
                                    style={{
                                        width: "30%",
                                        padding: "0.5rem",
                                        borderRadius: "5px",
                                        border: "1px solid #ccc",
                                    }}
                                    value={newPortfolioName}
                                    onChange={(e) => setNewPortfolioName(e.target.value)}
                                    placeholder="Nom du portefeuille"
                                />
                                <Button onClick={handleCreatePortfolio}>Créer</Button>
                            </div>

                            {portfolios.length > 0 && (
                                <select
                                    className="p-2 border rounded"
                                    value={selectedPortfolioName}
                                    onChange={(e) => setSelectedPortfolioName(e.target.value)}
                                >
                                    <option value="">Sélectionnez un portefeuille</option>
                                    {portfolios.map((p) => (
                                        <option key={p.name} value={p.name}>
                                            {p.name}
                                        </option>
                                    ))}
                                </select>
                            )}

                            {selectedPortfolio && (
                                <div className="mt-4">
                                    <h3 className="text-md font-semibold">Ajouter / Modifier un actif</h3>
                                    <div className="flex space-x-2 my-2">
                                        <input
                                            className="p-2 border rounded w-1/2"
                                            style={{
                                                width: "30%",
                                                padding: "0.5rem",
                                                borderRadius: "5px",
                                                border: "1px solid #ccc",
                                            }}
                                            value={newAsset.symbol}
                                            onChange={(e) => setNewAsset({ ...newAsset, symbol: e.target.value })}
                                            placeholder="Symbole (ex: BTC)"
                                        />
                                        <input
                                            className="p-2 border rounded w-1/4"
                                            style={{
                                                width: "30%",
                                                padding: "0.5rem",
                                                borderRadius: "5px",
                                                border: "1px solid #ccc",
                                            }}
                                            type="number"
                                            value={newAsset.quantity}
                                            onChange={(e) => setNewAsset({ ...newAsset, quantity: e.target.value })}
                                            placeholder="Quantité"
                                        />
                                        <Button
                                            onClick={() =>
                                                handleAddAsset(
                                                    selectedPortfolio.name,
                                                    newAsset.symbol,
                                                    newAsset.quantity
                                                )
                                            }
                                        >
                                            Ajouter / Modifier
                                        </Button>
                                    </div>

                                    {/* Tableau listant les actifs existants */}
                                    <h3 className="text-md font-semibold mt-4">Actifs du portefeuille</h3>
                                    <table className="styled-table">
                                        <thead>
                                        <tr>
                                            <th>Symbole</th>
                                            <th>Quantité</th>
                                            <th>Prix Unitaire</th>
                                            <th>Valeur Totale</th>
                                            <th>Actions</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {Object.entries(selectedPortfolio.assets).map(([symbol, qty]) => {
                                            const unitPrice = prices[symbol];
                                            const totalValue =
                                                unitPrice !== undefined ? (unitPrice * qty).toFixed(2) : "...";

                                            return (
                                                <tr key={symbol}>
                                                    {/* Au clic sur la cellule "Symbole", on sélectionne le symbole */}
                                                    <td
                                                        style={{ cursor: "pointer" }}
                                                        onClick={() => handleSymbolClick(symbol)}
                                                    >
                                                        {symbol}
                                                    </td>
                                                    <td>{qty}</td>
                                                    <td>{unitPrice !== undefined ? unitPrice.toFixed(2) : "..."}</td>
                                                    <td>{totalValue}</td>
                                                    <td>
                                                        <button
                                                            onClick={() =>
                                                                handleAddAsset(selectedPortfolio.name, symbol, -1)
                                                            }
                                                        >
                                                            -1
                                                        </button>
                                                        <button
                                                            onClick={() =>
                                                                handleAddAsset(selectedPortfolio.name, symbol, 1)
                                                            }
                                                        >
                                                            +1
                                                        </button>
                                                        <button
                                                            onClick={() => handleRemoveAsset(selectedPortfolio.name, symbol)}
                                                            style={{ backgroundColor: "red", marginLeft: "5px" }}
                                                        >
                                                            Supprimer
                                                        </button>
                                                    </td>
                                                </tr>
                                            );
                                        })}
                                        </tbody>
                                    </table>
                                </div>
                            )}
                        </div>
                    </div>

                    {/* ===== Colonne de droite : le graphique historique du symbole cliqué ===== */}
                    <div style={{ flex: 1 }}>
                        {selectedSymbol && historicalData.length > 0 ? (
                            <MyChart data={historicalData} symbol={selectedSymbol} />
                        ) : (
                            <p>Sélectionnez un symbole (en cliquant dessus) pour voir son historique.</p>
                        )}
                    </div>
                </div>
            ) : (
                <p>Chargement des données...</p>
            )}
        </div>
    );
}

export default Welcome;
