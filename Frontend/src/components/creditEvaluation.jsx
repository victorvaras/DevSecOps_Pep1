import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import applyCredit from '../services/applyCredit';

import '@react-pdf-viewer/core/lib/styles/index.css';
import { Alert } from '@mui/material';

const CreditEvaluation = () => {
    const { id } = useParams(); // Obtiene el ID de la URL
    const [evaluationData, setEvaluationData] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await applyCredit.getApplyCreditById(id);
                setEvaluationData(response);
                console.log(typeof response.data.comprobante_ingresos);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };
        fetchData();
    }, [id]);

    if (!evaluationData) {
        return <div>Loading...</div>;
    }

    const downloadFile = (fileData, fileName) => {
        const blob = new Blob([new Uint8Array(fileData)], { type: 'application/pdf' });
        const url = URL.createObjectURL(blob);

        console.log( fileData);

        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', fileName);
        
        document.body.appendChild(link);
        link.click();
        link.parentNode.removeChild(link);
        URL.revokeObjectURL(url);
    };

    const handleNavigateRequirement = () => {
        navigate('/evaluacion-requerimientos/' + id);
    };

    const onClickAprobar = async () => {
        console.log('Aprobar clicked');
        try {
            const cambio = await applyCredit.putApplyCreditSeguimiento(id, 3);
            console.log(cambio);
            handleNavigateRequirement();
            
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }
    const handleCreditRequest = () => {
        navigate('/solicitudes-credito');
    };

    const onClickRechazar = async () => {
        console.log('Rechazar clicked');
        try {
            const cambio = await applyCredit.putApplyCreditSeguimiento(id, 2);
            console.log(cambio);
            handleCreditRequest();
            Alert('Solicitud Rechazada');
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }

    return (
        <div className="credit-evaluation-container" style={{ paddingTop: '1px', width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: '90px' }}>
            <h1>Evaluación de Crédito</h1>
            <div style={{ maxWidth: '800px', width: '100%' }}>
                <div className="credit-info">
                    <h2>Información de Crédito</h2>
                    <p><strong>ID Cliente:</strong> {evaluationData.data.id_cliente}</p>
                    <p><strong>ID Tipo Crédito:</strong> {evaluationData.data.id_Tipo_Prestamo}</p>
                    <p><strong>Monto Deseado:</strong> {evaluationData.data.monto_deseado}</p>
                    <p><strong>Plazo Deseado:</strong> {evaluationData.data.plazo_deseado} <strong>Años</strong></p>
                    <hr style={{ margin: '20px 0' }} />
                </div>
            </div> 
            <div className="client-documents" style={{ maxWidth: '800px', width: '100%', marginTop: '20px' }}>
                <h2>Documentos Cliente</h2>
                
                {evaluationData.data.id_Tipo_Prestamo === 1 && (
                    <div>
                        <p><strong>Comprobante de Ingresos:</strong> {evaluationData.data.comprobante_ingresos ? 'Sí' : 'No'}</p>
                        <p><strong>Certificado de Avalúo:</strong> {evaluationData.data.certificado_avaluo ? 'Sí' : 'No'}</p>
                        <p><strong>Historial Crediticio:</strong> {evaluationData.data.historial_crediticio ? 'Sí' : 'No'}</p>
                        
                        <button onClick={() => downloadFile(evaluationData.comprobante_ingresos, 'comprobante_ingresos.pdf')}>
                            Descargar Comprobante de Ingresos
                        </button>

                    </div>
                )}

                {evaluationData.data.id_Tipo_Prestamo === 2 && (
                    <div>
                        <p><strong>Comprobante de Ingresos:</strong> {evaluationData.data.comprobante_ingresos ? 'Sí' : 'No'}</p>
                        <p><strong>Certificado de Avalúo:</strong> {evaluationData.data.certificado_avaluo ? 'Sí' : 'No'}</p>
                        <p><strong>Escritura de la Primera Vivienda:</strong> {evaluationData.data.escritura_primera_vivienda ? 'Sí' : 'No'}</p>
                        <p><strong>Historial Crediticio:</strong> {evaluationData.data.historial_crediticio ? 'Sí' : 'No'}</p>
                        
                        <button onClick={() => downloadFile(evaluationData.comprobante_ingresos, 'comprobante_ingresos.pdf')}>
                            Descargar Comprobante de Ingresos
                        </button>
                    </div>
                )}

                {evaluationData.data.id_Tipo_Prestamo === 3 && (
                    <div>
                        <p><strong>Estado financiero del negocio:</strong> {evaluationData.data.estado_financiero_negocio ? 'Sí' : 'No'}</p>
                        <p><strong>Comprobante de Ingresos:</strong> {evaluationData.data.comprobante_ingresos ? 'Sí' : 'No'}</p>
                        <p><strong>Certificado de Avalúo:</strong> {evaluationData.data.certificado_avaluo ? 'Sí' : 'No'}</p>
                        <p><strong>Plan de Negocios:</strong> {evaluationData.data.plan_negocios ? 'Sí' : 'No'}</p>
                        
                        <button onClick={() => downloadFile(evaluationData.comprobante_ingresos, 'comprobante_ingresos.pdf')}>
                            Descargar Comprobante de Ingresos
                        </button>
                    </div>
                )}

                {evaluationData.data.id_Tipo_Prestamo === 4 && (
                    <div>
                        <p><strong>Comprobante de Ingresos:</strong> {evaluationData.data.comprobante_ingresos ? 'Sí' : 'No'}</p>
                        <p><strong>Presupuesto de la Remodelación:</strong> {evaluationData.data.presupuesto_remodelacion ? 'Sí' : 'No'}</p>
                        <p><strong>Certificado de Avalúo Actualizado:</strong> {evaluationData.data.certificado_avaluo_actualizado ? 'Sí' : 'No'}</p>
                        
                        <button onClick={() => downloadFile(evaluationData.comprobante_ingresos, 'comprobante_ingresos.pdf')}>
                            Descargar Comprobante de Ingresos
                        </button>
                    </div>
                )}


                <div style={{ display: 'flex', justifyContent: 'space-between', marginTop: '20px' }}>
                    <button 
                        style={{ backgroundColor: 'red', color: 'white', padding: '10px 20px', border: 'none', borderRadius: '5px' }}
                        onClick={() => onClickRechazar()}
                    >
                        Rechazar
                    </button>
                    <button 
                        style={{ backgroundColor: 'green', color: 'white', padding: '10px 20px', border: 'none', borderRadius: '5px' }}
                        onClick={() => onClickAprobar()}
                    >
                        Aprobar
                    </button>
                </div>


            </div>
        </div>
    );
};

export default CreditEvaluation;
