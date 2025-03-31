import React, { useState } from 'react';
import client from '../services/client';
import { useNavigate } from 'react-router-dom';


const UserForm = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        rut: '',
        nombre: '',
        apellido: '',
        contrasenia: '',
        identificacion: null,
        comprobante_ingresos: null,
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
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

        const rut_client = parseInt(formData.rut) ; // Mantener como cadena

        const formDataToSend = new FormData();
        formDataToSend.append('rut', rut_client);
        formDataToSend.append('nombre', formData.nombre);
        formDataToSend.append('apellido', formData.apellido);
        formDataToSend.append('contrasenia', formData.contrasenia);

        formDataToSend.append('identificacion', formData.identificacion);
        formDataToSend.append('comprobante_ingresos', formData.comprobante_ingresos);

        console.log(rut_client);
        console.log(formDataToSend.get('comprobante_ingresos'));

        


        try {
            
            const response = await client.createClient(formDataToSend);
            

            alert('Usuario creado con éxito');
            console.log('Respuesta del backend:', response.data);
            handleNavigateToInicio();
        } catch (error) {
            alert('Error al crear el usuario: ' + (error.response ? error.response.data : error.message));
            console.error('Error:', error);
        }
    };

    return (
        <div>
            <h1>Registro de Usuario</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>RUT:</label>
                    <input
                        type="number" // Cambiado de "int" a "text"
                        name="rut"
                        value={formData.rut}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div>
                    <label>Nombre:</label>
                    <input
                        type="text"
                        name="nombre"
                        value={formData.nombre}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div>
                    <label>Apellido:</label>
                    <input
                        type="text"
                        name="apellido"
                        value={formData.apellido}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div>
                    <label>Contraseña:</label>
                    <input
                        type="password"
                        name="contrasenia"
                        value={formData.contrasenia}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div>
                    <label>Identificación:</label>
                    <input
                        type="file"
                        name="identificacion"
                        onChange={handleFileChange}
                        accept="application/pdf"
                        required
                    />
                </div>
                

                <div>
                    <label>Comprobante de Ingresos:</label>
                    <input
                        type="file"
                        name="comprobante_ingresos"
                        onChange={handleFileChange}
                        accept="application/pdf"
                        required
                    />
                </div>

                <button type="submit">Registrar</button>
            </form>
        </div>
    );
};

export default UserForm;
