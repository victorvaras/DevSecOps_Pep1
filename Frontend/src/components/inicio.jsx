import React from 'react';
import { useNavigate } from 'react-router-dom';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';

const Inicio = () => {
    const navigate = useNavigate();

    const handleSimularCredito = () => {
        navigate('/simulacion');
    };

    const handleRegisterClient = () => {
        navigate('/registerClient');
    };

    const handleLoginClient = () => {
        navigate('/login');
    };

    const handleCreditRequest = () => {
        navigate('/solicitudes-credito');
    };

    return (
        <Stack spacing={2} direction="row">
            
            <Button variant="contained" onClick={handleSimularCredito}> Simulacion de credito</Button>
            <Button variant="contained" onClick={handleRegisterClient}>Registrar nuevo cliente</Button>
            <Button variant="contained" onClick={handleLoginClient}>Login Cliente</Button>
            <Button variant="contained" onClick={handleCreditRequest}>Solicitudes de Credito</Button>
            
            
        </Stack>
    );
};

export default Inicio;
