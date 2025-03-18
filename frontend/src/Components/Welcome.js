import { useLocation } from "react-router-dom";

function Welcome() {
    const location = useLocation();
    const username = location.state?.username || "Utilisateur";

    return (
        <div>
            <h1>Bienvenue {username} !</h1>
            <p>Vous êtes maintenant connecté.</p>
        </div>
    );
}

export default Welcome;
