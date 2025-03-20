import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../Styles/Login.css"; // Nous allons créer ce fichier CSS

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        setIsLoading(true);

        try {
            const response = await fetch("http://localhost:9000/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username, password }),
            });

            const data = await response.json();
            if (response.ok) {
                setMessage(`Bienvenue, ${data.username}`);
                setIsLoading(false);

                // Ajouter une classe pour l'animation de succès
                document.querySelector('.login-form').classList.add('success');

                setTimeout(() => {
                    navigate("/welcome", { state: { username: data.username } });
                }, 1500);
            } else {
                setMessage(data.message);
                setIsLoading(false);
                // Ajouter une classe pour l'animation d'erreur
                document.querySelector('.login-form').classList.add('error');
                setTimeout(() => {
                    document.querySelector('.login-form').classList.remove('error');
                }, 500);
            }
        } catch (error) {
            setMessage("Erreur de connexion. Vérifie ton serveur.");
            setIsLoading(false);
            // Ajouter une classe pour l'animation d'erreur
            document.querySelector('.login-form').classList.add('error');
            setTimeout(() => {
                document.querySelector('.login-form').classList.remove('error');
            }, 500);
        }
    };

    return (
        <div className="login-container">
            <div className="login-card">
                <div className="login-header">
                    <img src="/images/CYTechLogo.png" alt="CYTech Logo" className="logo" />
                    <h2>Connexion</h2>
                </div>

                {message && (
                    <div className={`message ${message.includes("Bienvenue") ? "success" : "error"}`}>
                        {message}
                    </div>
                )}

                <form onSubmit={handleLogin} className="login-form">
                    <div className="input-group">
                        <label htmlFor="username">Nom d'utilisateur</label>
                        <input
                            id="username"
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </div>

                    <div className="input-group">
                        <label htmlFor="password">Mot de passe</label>
                        <input
                            id="password"
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>

                    <button
                        type="submit"
                        className={`login-button ${isLoading ? 'loading' : ''}`}
                        disabled={isLoading}
                    >
                        {isLoading ? (
                            <span className="spinner"></span>
                        ) : (
                            "Se connecter"
                        )}
                    </button>
                </form>

                <div className="login-footer">
                    <p>Projet de Programmation Fonctionnelle</p>
                </div>
            </div>
        </div>
    );
}

export default Login;
