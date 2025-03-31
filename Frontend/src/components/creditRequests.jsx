import React, { useEffect, useState } from 'react';
import TableContainer from '@mui/material/TableContainer';
import Table from '@mui/material/Table';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';
import TableBody from '@mui/material/TableBody';
import Paper from '@mui/material/Paper';
import applyCredit from '../services/applyCredit';
import clienteServices from '../services/client';
import loanType from '../services/loanType';
import { useNavigate } from 'react-router-dom';





const CreditRequests = () => {
    const [data, setData] = useState(null);
    const [clientNames, setClientNames] = useState({});
    const [loanTypeData, setLoanTypeData] = useState({});

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);


    const navigate = useNavigate();

    const evaluateRequest = (id) => {
        navigate(`/evaluacion-credito/${id}`);
    };

    const requestStatus = (id) => {
        navigate(`/estado-solicitud/${id}`);
    };



  // Fetch data for credit requests
useEffect(() => {
    applyCredit.getApplyCredit()
        .then(response => {
            const sortedData = response.data.sort((a, b) => a.id_solicitud_credito - b.id_solicitud_credito);
            setData(sortedData);
            setLoading(false);
        })
        .catch(error => {
            setError(error);
            setLoading(false);
        });
}, []);


   
    const fetchClientById = (id) => {
        
        console.log(id)
        clienteServices.getClient(id)
            .then(response => {
                setClientNames(prevState => ({
                    ...prevState,
                    [id]: response.data.apellido,
                }));
            })
            .catch(error => {
                console.error('Error fetching client:', error);
            });
    };

    const fetchLoanTypeById = (id) => {
        loanType.getLoanType(id)
            .then(response => {
                setLoanTypeData(prevState => ({
                    ...prevState,
                    [id]: response.data.nombreTipo_Prestamo,
                }));
            })
            .catch(error => {
                console.error('Error fetching loan type:', error);
            });
    }



    
    useEffect(() => {
        if (data && data.length > 0) {
            data.forEach(item => {
                if (!clientNames[item.id_cliente]) {
                    fetchClientById(item.id_cliente);
                }                
                if (!loanTypeData[item.id_Tipo_Prestamo]) {
                    fetchLoanTypeById(item.id_Tipo_Prestamo);
                }
            });
        }
    }, [data]);

    if (loading) {
        return <p>Cargando solicitudes...</p>;
    }

    if (error) {
        return <p>Error al cargar las solicitudes: {error.message}</p>;
    }

    return (
        <div style={{ marginTop: '120px' }}>
            <h1>Lista de Solicitudes de Crédito</h1>
            <TableContainer component={Paper} style={{ marginTop: '20px' }}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Nombre Cliente</TableCell>
                            <TableCell>Tipo Crédito</TableCell>
                            <TableCell>Estado Solicitud</TableCell>
                            <TableCell>Acciones</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {data && data.length > 0 ? (
                            data.map((item) => (
                                <TableRow key={item.id_solicitud_credito}>
                                    <TableCell>{item.id_solicitud_credito}</TableCell>
                                    <TableCell>{clientNames[item.id_cliente] || 'Cargando nombre...'}</TableCell>
                                    <TableCell>{loanTypeData[item.id_Tipo_Prestamo] || 'Cargando tipo prestamo'}</TableCell>
                                    <TableCell>{item.id_seguimiento_solicitud}</TableCell>
                                    <TableCell>
                                        <button onClick={() => evaluateRequest(item.id_solicitud_credito)}>
                                            Evaluar Solicitud
                                        </button>
                                    </TableCell>
                                    <TableCell>
                                        <button onClick={() => requestStatus(item.id_solicitud_credito)}>
                                            Revisar estado de solicitud
                                        </button>
                                    </TableCell>
                                </TableRow>
                            ))
                        ) : (
                            <TableRow>
                                <TableCell colSpan={5} align="center">
                                    No hay solicitudes disponibles.
                                </TableCell>
                            </TableRow>
                        )}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
};

export default CreditRequests;
