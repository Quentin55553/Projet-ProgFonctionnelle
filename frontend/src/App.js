import './App.css';
import './style.css';
import { Routes, Route } from "react-router-dom";
import Login from "./Components/Login";
import Welcome from "./Components/Welcome";

function App() {
    return (
        <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/login" element={<Login />} />
            <Route path="/welcome" element={<Welcome />} />
        </Routes>
    );
}

export default App;
