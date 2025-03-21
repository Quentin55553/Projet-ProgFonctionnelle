import { useState } from "react";
import { useNavigate } from "react-router-dom";


function Register() {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const navigate = useNavigate();


    const handleRegister = async (e) => {
        e.preventDefault();

        const response = await fetch("http://localhost:9000/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username, email, password }),
        });

        const data = await response.json();

        if (response.ok) {
            setMessage("Compte créé avec succès !");
            setTimeout(() => navigate("/login"), 2000); // Redirige vers connexion après 2 sec
        } else {
            setMessage(data.message);
        }
    };

    return (
        <div>
            <h2>Inscription</h2>

            {message && <p>{message}</p>}

            <form onSubmit={handleRegister}>
                <input style={{ width: "30%",
                                padding: "0.5rem",
                                borderRadius: "5px",
                                border: "1px solid #ccc",
                                }}
                       type="text"
                       placeholder="Nom d'utilisateur"
                       value={username} onChange={(e) => setUsername(e.target.value)} required />
                <input  style={{ width: "30%",
                                padding: "0.5rem",
                                borderRadius: "5px",
                                border: "1px solid #ccc",
                            }}
                        type="email"
                        placeholder="Email"
                        value={email} onChange={(e) => setEmail(e.target.value)} required />
                <input  style={{ width: "30%",
                                padding: "0.5rem",
                                borderRadius: "5px",
                                border: "1px solid #ccc",
                            }}
                        type="password"
                        placeholder="Mot de passe"
                        value={password} onChange={(e) => setPassword(e.target.value)} required />
                <button type="submit">S'inscrire</button>
            </form>
        </div>
    );
}

export default Register;
