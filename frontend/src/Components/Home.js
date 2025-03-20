import React from "react";
import { Link } from "react-router-dom";
import "../Styles/Home.css";

function Home() {
    return (
        <div className="home-container">
            <div className="home-header">
                <img src="/images/CYTechLogo.png" alt="CYTech Logo" className="logo" />
                <h1>Bienvenue sur notre Plateforme Financière</h1>
                <p className="subtitle">
                    Suivez vos actifs financiers et restez informé des dernières tendances du marché
                </p>
            </div>

            <div className="features-section">
                <div className="feature-card">
                    <div className="feature-icon">📈</div>
                    <h3>Suivi en temps réel</h3>
                    <p>Accédez aux données financières actualisées pour prendre des décisions éclairées</p>
                </div>

                <div className="feature-card">
                    <div className="feature-icon">💼</div>
                    <h3>Gestion de portefeuille</h3>
                    <p>Gérez facilement vos investissements et suivez leurs performances</p>
                </div>

                <div className="feature-card">
                    <div className="feature-icon">📊</div>
                    <h3>Analyses détaillées</h3>
                    <p>Visualisez des graphiques et analyses pour mieux comprendre les tendances</p>
                </div>
            </div>

            <div className="cta-section">
                <h2>Prêt à commencer?</h2>
                <div className="cta-buttons">
                    <Link to="/register" className="cta-button register">
                        Créer un compte
                    </Link>
                    <Link to="/login" className="cta-button login">
                        Se connecter
                    </Link>
                </div>
            </div>

            <div className="home-footer">
                <p>Projet de Programmation Fonctionnelle - CY Tech</p>
            </div>
        </div>
    );
}

export default Home;
