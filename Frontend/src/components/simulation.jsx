import React, { useState } from 'react';
import simulation from "../services/credit_simulation";
import loanType from '../services/loanType';

const Simulation = () => {
    
    const [formData, setFormData] = useState({
        monto_deseado: '',
        plazo_deseado: '',
        id_Tipo_Prestamo: ''
    });

    const [cuota, setCuota] = useState(null);
    const [tasaInteres, setTasaInteres] = useState(null);
    const [monto_deseado, setMonto_deseado] = useState(null);
    const [plazo_deseado, setPlazo_deseado] = useState(null);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        // Convertimos los valores a número
        const montoDeseadoValue = parseFloat(formData.monto_deseado);
        const plazoDeseadoValue = parseInt(formData.plazo_deseado);
        const idTipoPrestamoValue = parseInt(formData.id_Tipo_Prestamo);

        // Lógica de validación
        if (isNaN(montoDeseadoValue) || isNaN(plazoDeseadoValue) || isNaN(idTipoPrestamoValue)) {
            alert('Por favor, ingrese datos válidos');
            return;
        }
        
        // Datos a enviar al backend
        const dataToSend = {
            monto_deseado: montoDeseadoValue,
            plazo_deseado: plazoDeseadoValue,
            id_Tipo_Prestamo: idTipoPrestamoValue
        };

        

        try {
            // Realiza la solicitud al backend utilizando el servicio `create`
            const response = await simulation.createSimulacion(dataToSend);
            
            const tipoPrestamo = await loanType.getLoanType(idTipoPrestamoValue);

            const valorCuota = response.data.valor_cuota;
            const tasaInteres = tipoPrestamo.data.tasa_anual;
            const monto_deseado = response.data.monto_deseado;
            const plazo_deseado = response.data.plazo_deseado;
            setCuota(valorCuota);
            setTasaInteres(tasaInteres);
            setMonto_deseado(monto_deseado);
            setPlazo_deseado(plazo_deseado);

            alert('Simulación creada con éxito');
            console.log('Respuesta del backend:', response.data);
        } catch (error) {
            alert('Error al crear la simulación');
            console.error('Error:', error);
        }

        
    };

    return (
        <div>
            <h1>Simulación de Crédito</h1>
            <form onSubmit={handleSubmit}>
                
                <div>
                    <label>Monto deseado:</label>
                    <input
                        type="number"
                        name="monto_deseado"
                        value={formData.monto_deseado}
                        onChange={handleChange}
                    />
                </div>

                <div>
                    <label>Plazo deseado:</label>
                    <input
                        type="number"
                        name="plazo_deseado"
                        value={formData.plazo_deseado}
                        onChange={handleChange}
                    />
                </div>

                <div>
                    <label>Tipo de Crédito:</label>
                    <select
                        name="id_Tipo_Prestamo"
                        value={formData.id_Tipo_Prestamo}
                        onChange={handleChange}
                    >
                        <option value="">Seleccione una opción</option>
                        <option value="1">Primera Vivienda</option>
                        <option value="2">Segunda Vivienda</option>
                        <option value="3">Propiedades Comerciales</option>
                        <option value="4">Remodelación</option>
                    </select>
                </div>

                <button type="submit">Simular credito (Chile)</button>
            </form>

            {cuota !== null && ( // Mostrar el valor de la cuota si existe
                <div>
                    <div>
                        <h2>Resultados de la Simulación</h2>
                        <p><strong>Valor de la Cuota:</strong> {cuota}</p>
                        <p><strong>Tasa de Interés Anual:</strong> {tasaInteres}%</p>
                        <p><strong>Monto Deseado:</strong> {monto_deseado}</p>
                        <p><strong>Plazo Deseado:</strong> {plazo_deseado} años</p>
                       
                    </div>
                </div>
            )}


        </div>
    );
};

export default Simulation;
