import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import client from '../services/client';

const Login = () => {
    const [rut, setRut] = useState('');
    const [password, setPassword] = useState('');
    const [loginStatus, setLoginStatus] = useState(null);
    const [id_Usuario, setId_Usuario] = useState(null);

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        const handleNavigationApplyCredit = () => {
            navigate('/solicitar-credito');
        };
        

        const rut_client = parseInt(rut) ;
        

        const dataClient = {
            rut: rut_client,
            contrasenia: password
        };

        console.log(dataClient);

        try{
            const response = await client.loginClient(dataClient);
            console.log(response);

            if (response.data === 0) {
                
                setLoginStatus('Credenciales inválidas');
                

            } else {    
                
                setLoginStatus('Success');
                setId_Usuario(21)
                localStorage.setItem('id_Usuario', response.data);
                handleNavigationApplyCredit();
               
            }
        }
        catch (error) {
            alert('Error al logear el usuario: ' + (error.response ? error.response.data : error.message));
            console.error('Error:', error);
                }
    };


    return (
        <div className="login-container">
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="rut">RUT:</label>
                    <input
                        type="text"
                        id="rut"
                        value={rut}
                        onChange={(e) => setRut(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Login</button>
            </form>

            {loginStatus && <p>{loginStatus}</p>}
        </div>
    );
};

export default Login;