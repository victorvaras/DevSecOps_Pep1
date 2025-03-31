import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import applyCredit from '../services/applyCredit';
import requestTracking from '../services/requestTracking';
import businessLogic from '../services/businessLogic';

const RequestStatus = () => {
    const { id } = useParams();
    const [solicitudCredito, setSolicitudCredito] = useState(null);
    const [estadoSolicitud, setEstadoSolicitud] = useState(null);
    const [loading, setLoading] = useState(true);
    const [dataCostosTotales, setDataCostosTotales] = useState(null);

    useEffect(() => {
        obtenerSolicitud();
    }, []);

    const costosTotales = async () => {
        try {
            const respuesta = await businessLogic.costo_total_credito(id);
            console.log("Respuesta de costosTotales:", respuesta);
            setDataCostosTotales(respuesta.data);
        } catch (error) {
            console.error("Error al obtener costos totales:", error);
        }
    };

    const obtenerSolicitud = async () => {
        try {
            // Obtener la solicitud de crédito
            const respuestaSolicitud = await applyCredit.getApplyCreditById(id);
            const dataSolicitud = respuestaSolicitud.data;
            setSolicitudCredito(dataSolicitud);

            // Obtener el estado de la solicitud si `solicitudCredito` está definido
            if (dataSolicitud && dataSolicitud.id_seguimiento_solicitud) {
                const respuestaEstado = await requestTracking.getSeguimientoSolicitud(dataSolicitud.id_seguimiento_solicitud);
                setEstadoSolicitud(respuestaEstado.data);
                costosTotales(); // Llamada a costosTotales
            }
        } catch (error) {
            console.log(error);
        } finally {
            setLoading(false);
        }
    };

    if (loading) {
        return <p>Cargando...</p>;
    }

    const buttonAceptarCredito = async () => {
        try {
            await applyCredit.putApplyCreditSeguimiento(id, 5);
            console.log("Crédito aceptado");
            alert("Crédito aceptado");
        } catch (error) {
            console.error("Error al aceptar crédito:", error);
        }
    }

    const buttonRechazarCredito = async () => {
        try {
            await applyCredit.putApplyCreditSeguimiento(id, 8);
            console.log("Crédito rechazado");
            alert("Crédito rechazado");
        } catch (error) {
            console.error("Error al rechazar crédito:", error);
        }
    }

    return (
        <div style={{ padding: '20px', marginTop: '80px' }}>
            <h1>Estado de Solicitud</h1>
            <p>Aquí puedes ver el estado de tu solicitud.</p>
            <p>ID de la solicitud: {id}</p>
            
            <p>Estado: {estadoSolicitud ? estadoSolicitud.nombre_seguimiento_solicitud : "Cargando..."}</p>
            <p>Descripción: {estadoSolicitud ? estadoSolicitud.descripcion_seguimiento_solicitud : "Cargando"}</p>
            
            {solicitudCredito && solicitudCredito.id_seguimiento_solicitud === 4 && (
                <div style={{ marginTop: '20px' }}>
                    <h2>Costos Totales del Crédito</h2>
                    <p>Monto Solicitado: {solicitudCredito.monto_deseado}</p>
                    <p>Seguro de desgravamen: {dataCostosTotales ? dataCostosTotales.seguro_desgravamen : "cargando"}</p>                
                    <p>Seguro de incendio: {dataCostosTotales ? dataCostosTotales.seguro_incendio : "cargando"}</p>
                    <p>Comisión de Administración: {dataCostosTotales ? dataCostosTotales.comision_administracion : "cargando"}</p>
                    <p>Costo Mensual: {dataCostosTotales ? dataCostosTotales.costo_mensual_real : "cargando"}</p>
                    <p>Total a Pagar: {dataCostosTotales ? dataCostosTotales.costo_total : "cargando"}</p>

                    <div style={{ marginTop: '20px' }}>
                        <button onClick={() => buttonAceptarCredito() }  style={{ backgroundColor: 'green', color: 'white', padding: '10px 20px', marginRight: '10px' }}>
                            Aceptar Crédito
                        </button>
                        
                    </div>
                </div>
            )}
            <button onClick={() => buttonRechazarCredito() } style={{ backgroundColor: 'red', color: 'white', padding: '10px 20px' }}>
                            Rechazar Crédito
                        </button>
        </div>
    ); 
};

export default RequestStatus;
