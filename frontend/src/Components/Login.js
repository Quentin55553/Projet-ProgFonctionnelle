import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const navigate = useNavigate(); // Utilise useNavigate pour rediriger

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch("http://localhost:9000/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username, password }),
            });

            const data = await response.json();
            if (response.ok) {
                setMessage(`Bienvenue, ${data.username}`);
                setTimeout(() => {
                    navigate("/welcome"); // Redirige vers la page Welcome
                }, 1500);
            } else {
                setMessage(data.message);
            }
        } catch (error) {
            setMessage("Erreur de connexion. Vérifie ton serveur.");
        }
    };

    return (
        <div>
            <h2>Connexion</h2>
            {message && <p>{message}</p>}
            <form onSubmit={handleLogin}>
                <input
                    type="text"
                    placeholder="Nom d'utilisateur"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Mot de passe"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <button type="submit">Se connecter</button>
            </form>
        </div>
    );
}

export default Login;
