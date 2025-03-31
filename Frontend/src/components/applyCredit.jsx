import React, { useState } from 'react';
import applyCredit from '../services/applyCredit';
import { useNavigate, useLocation } from 'react-router-dom';

const ApplyCredit = () => {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        id_Tipo_Prestamo: 0,
        id_cliente: 0,
        monto_deseado: 0,
        plazo_deseado: 0,
        comprobante_ingresos: null,
        certificado_avaluo: null,
        historial_crediticio: null,
        escritura_primera_vivienda: null,
        estado_financiero_negocio: null,
        plan_negocios: null,
        presupuesto_remodelacion: null,
        certificado_avaluo_actualizado: null,
    });


    const [loanType, setLoanType] = useState('');
    const [selectedFiles, setSelectedFiles] = useState({});
    const [loanAmount, setLoanAmount] = useState('');
    const [loanTerm, setLoanTerm] = useState('');

    const location = useLocation();
    const id_Usuario = localStorage.getItem('id_Usuario');

    const handleLoanTypeChange = (e) => {
        const selectedLoanType = e.target.value;
        setLoanType(selectedLoanType);
        formData.id_Tipo_Prestamo = selectedLoanType;
    };

    const handleFileChange = (e) => {
        const { name, files } = e.target;
        setFormData({
            ...formData,
            [name]: files[0] 
        });
    };


    const handleNavigateToInicio = () => {
        navigate('/');
    }; 




    const handleSubmit = async (e) => {
        e.preventDefault();
        const idTipoPrestamoValue = parseInt(loanType);

        

        const formDataToSend = new FormData();

        formDataToSend.append('id_cliente', id_Usuario);
        formDataToSend.append('id_Tipo_Prestamo', idTipoPrestamoValue);
        formDataToSend.append('monto_deseado', loanAmount);
        formDataToSend.append('plazo_deseado', loanTerm);


        if (formData.comprobante_ingresos) {
            formDataToSend.append('comprobante_ingresos', formData.comprobante_ingresos);
        }
        if (formData.certificado_avaluo) {
            formDataToSend.append('certificado_avaluo', formData.certificado_avaluo);
        }
        if (formData.historial_crediticio) {
            formDataToSend.append('historial_crediticio', formData.historial_crediticio);
        }
        if (formData.escritura_primera_vivienda) {
            formDataToSend.append('escritura_primera_vivienda', formData.escritura_primera_vivienda);
        }
        if (formData.estado_financiero_negocio) {
            formDataToSend.append('estado_financiero_negocio', formData.estado_financiero_negocio);
        }
        if (formData.plan_negocios) {
            formDataToSend.append('plan_negocios', formData.plan_negocios);
        }
        if (formData.presupuesto_remodelacion) {
            formDataToSend.append('presupuesto_remodelacion', formData.presupuesto_remodelacion);
        }
        if (formData.certificado_avaluo_actualizado) {
            formDataToSend.append('certificado_avaluo_actualizado', formData.certificado_avaluo_actualizado);
        }

        try {

            const response = await applyCredit.createApllyCredit(formDataToSend);
            alert('Solicitud de crédito enviada con éxito');
            handleNavigateToInicio();
        } catch (error) {
            alert('Error al enviar la solicitud de crédito: ' + (error.response ? error.response.data : error.message));
           
        }

    };

    return (
        <div>
            <h1>Solicitud de Crédito</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Tipo de Préstamo:</label>
                    <select value={loanType} onChange={ (e) => setLoanType(e.target.value)}>
                        <option value="">Seleccione</option>
                        <option value="1">Primera Vivienda</option>
                        <option value="2">Segunda Vivienda</option>
                        <option value="3">Propiedades Comerciales</option>
                        <option value="4">Remodelación</option>
                    </select>
                </div>
                <div>
                    <label>Monto a Solicitar:</label>
                    <input type="number" value={loanAmount} onChange={(e) => setLoanAmount(e.target.value)} required />
                </div>
                <div>
                    <label>Plazo en Años:</label>
                    <input type="number" value={loanTerm} onChange={(e) => setLoanTerm(e.target.value)} required />
                </div>

                { (loanType === "1" || loanType === "2" || loanType === "3" || loanType === "4") && (
                    <div>
                    <label>Comprobante de ingresos:</label>
                    <input
                        type="file"
                        name="comprobante_ingresos"
                        onChange={handleFileChange}
                        accept="application/pdf"
                    />
                </div>
                )}

                { (loanType === "1" || loanType === "2" || loanType === "3") && (
                    <div>
                    <label>Certificado de avalúo:</label>
                    <input
                        type="file"
                        name="certificado_avaluo"
                        onChange={handleFileChange}
                        accept="application/pdf"
                    />
                </div>
                )}

                { (loanType === "1" || loanType === "2") && (
                    <div>
                    <label>Historial crediticio:</label>
                    <input
                        type="file"
                        name="historial_crediticio"
                        onChange={handleFileChange}
                        accept="application/pdf"
                    />
                </div>
                )}

                { (loanType === "2") && (
                    <div>
                    <label>Escritura de la primera vivienda:</label>
                    <input
                        type="file"
                        name="escritura_primera_vivienda"
                        onChange={handleFileChange}
                        accept="application/pdf"
                    />
                </div>
                )}

                { (loanType === "3") && (
                    <div>
                    <label>Estado financiero del negocio:</label>
                    <input
                        type="file"
                        name="estado_financiero_negocio"
                        onChange={handleFileChange}
                        accept="application/pdf"
                    />
                </div>
                )}

                { (loanType === "3") && (
                    <div>
                    <label>Plan de negocios:</label>
                    <input
                        type="file"
                        name="plan_negocios"
                        onChange={handleFileChange}
                        accept="application/pdf"
                    />
                </div>
                )}

                { (loanType === "4") && (
                    <div>
                    <label>Presupuesto de la remodelación:</label>
                    <input
                        type="file"
                        name="presupuesto_remodelacion"
                        onChange={handleFileChange}
                        accept="application/pdf"
                    />
                </div>
                )}

                { (loanType === "4") && (
                    <div>
                    <label>Certificado de avalúo actualizado:</label>
                    <input
                        type="file"
                        name="certificado_avaluo_actualizado"
                        onChange={handleFileChange}
                        accept="application/pdf"
                    />
                </div>
                )}


                <button type="submit">Enviar Solicitud</button>
            </form>
        </div>
    );
};

export default ApplyCredit;
