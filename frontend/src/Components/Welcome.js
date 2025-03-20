import { useLocation } from "react-router-dom";
import React, { useState } from "react";
import { Link } from "react-router-dom";
import '../App.css';


function Welcome() {
    const location = useLocation();
    const username = location.state?.username || "{UTILISATEUR}";

    const [notifications, setNotifications] = useState([]);
    const [menuOpen, setMenuOpen] = useState(false);

    const toggleMenu = () => {
        setMenuOpen(!menuOpen);
    };

    return (
        <div>
            <nav className="navbar">
                <ul className="navbar-links">
                    <li className="navbar-item">
                        <Link to="/welcome" className="navbar-link">
                            Accueil
                        </Link>
                    </li>
                    <li className="navbar-item">
                        <Link to="/" className="navbar-link">
                            Formulaire Actifs
                        </Link>
                    </li>
                </ul>
                <div className="notification-container">
                    <div className="notification-icon" onClick={toggleMenu}>
                        <span role="img" aria-label="notification">
                            🔔
                        </span>
                        {notifications.length > 0 && (
                            <span className="notification-badge">
                                {notifications.length}
                            </span>
                        )}
                    </div>
                    {menuOpen && (
                        <div className="notification-menu">
                            {notifications.length > 0 ? (
                                notifications.map((notif, index) => (
                                    <div key={index} className="notification-item">
                                        {notif}
                                    </div>
                                ))
                            ) : (
                                <div className="notification-item">
                                    Aucune nouvelle notification
                                </div>
                            )}
                        </div>
                    )}
                </div>
            </nav>

            <br/><br/><br/>

            <div>
                <h1>Bienvenue {username} !</h1>
            </div>
        </div>
    );
}

export default Welcome;
