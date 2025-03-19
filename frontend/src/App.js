import { Routes, Route } from "react-router-dom";
import Login from "./Components/Login";
import Register from "./Components/Register";
import Home from "./Components/Home";
import Welcome from "./Components/Welcome"; // Assurez-vous que Welcome est bien importé

function App() {
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} /> {/* Correction ici */}
            <Route path="/welcome" element={<Welcome />} />
        </Routes>
    );
}

export default App;
