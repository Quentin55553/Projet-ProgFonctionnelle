import { Link } from "react-router-dom";

function Home() {
    return (
        <div>
            <h1>Bienvenue sur notre application</h1>
            <Link to="/login">
                <button>Se connecter</button>
            </Link>
            <Link to="/register">
                <button>S'inscrire</button>
            </Link>
        </div>
    );
}

export default Home;
