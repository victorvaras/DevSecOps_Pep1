import React from 'react';
import { useNavigate } from 'react-router-dom'; 
import Button from '@mui/material/Button';

const BarraSuperior = () => {
    const navigate = useNavigate(); 

    const handleInicio = () => {
        navigate('/');  
    };

    return (
        <div style={{ 
            margin: "0", 
            padding: "4px 0", 
            backgroundColor: "lightgreen", 
            position: "fixed", 
            top: 0, 
            left: 0, 
            width: "100%", 
            textAlign: "center",
            fontSize: "10px"
        }}>
            <h1>PrestaBanco</h1>

            <button onClick={handleInicio} style={{ margin: "10px", padding: "5px 10px", fontSize: "10px" }}>
                Inicio
            </button>

        </div>
    );
};

export default BarraSuperior;
