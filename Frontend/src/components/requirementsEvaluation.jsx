import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import businessLogic from '../services/businessLogic';
import creditEvaluation from '../services/creditEvaluation';

const RequirementsEvaluation = () => {
    const { id } = useParams();
    const [requerimientoActual, setRequerimientoActual] = useState(null);
    const [ingresoCliente, setIngresoCliente] = useState('');
    const [deudaCliente, setDeudaCliente] = useState('');
    const [valorPropiedad, setValorPropiedad] = useState('');
    const [edadCliente, setEdadCliente] = useState('');
    const [saldoCuenta, setSaldoCuenta] = useState('');
    const [mes_1, setMes_1] = useState('0');
    const [mes_2, setMes_2] = useState('0');
    const [mes_3, setMes_3] = useState('0');
    const [mes_4, setMes_4] = useState('0');
    const [mes_5, setMes_5] = useState('0');
    const [mes_6, setMes_6] = useState('0');
    const [mes_7, setMes_7] = useState('0');
    const [mes_8, setMes_8] = useState('0');
    const [mes_9, setMes_9] = useState('0');
    const [mes_10, setMes_10] = useState('0');
    const [mes_11, setMes_11] = useState('0');
    const [mes_12, setMes_12] = useState('0');
    const [mes_1_ret, setMes_1_ret] = useState('');
    const [mes_2_ret, setMes_2_ret] = useState('');
    const [mes_3_ret, setMes_3_ret] = useState('');
    const [mes_4_ret, setMes_4_ret] = useState('');
    const [mes_5_ret, setMes_5_ret] = useState('');
    const [mes_6_ret, setMes_6_ret] = useState('');
    const [mes_7_ret, setMes_7_ret] = useState('');
    const [mes_8_ret, setMes_8_ret] = useState('');
    const [mes_9_ret, setMes_9_ret] = useState('');
    const [mes_10_ret, setMes_10_ret] = useState('');
    const [mes_11_ret, setMes_11_ret] = useState('');
    const [mes_12_ret, setMes_12_ret] = useState('');
    const [antiguedadCliente, setAntiguedadCliente] = useState('');
    const [dataRequerimientos, setDataRequerimientos] = useState(null);
    const [evaluacionSolicitud, setEvaluacionSolicitud] = useState('');
    const navigate = useNavigate();
    

    useEffect(() => {
        
        setRequerimientoActual(1);
        
    }, [id]);

    
    const evaluarR1 = async () => {
        const R1 = await businessLogic.validate_R1(ingresoCliente, id);       

        if (R1.data === true) {
            alert('Requerimiento 1 aprobado');
            setRequerimientoActual(2);
        }
        else {
            alert('Requerimiento 1 rechazado');
            setRequerimientoActual(100);
        }
    }

    const evaluarR2 = async (aprobado) => {
        const R2 = await businessLogic.validate_R2(id, aprobado);
        if (R2.data === true) {
            alert('Requerimiento 2 aprobado');
            setRequerimientoActual(3); // Assuming there's a next step after Requerimiento 2
        } else {
            alert('Requerimiento 2 rechazado');
            setRequerimientoActual(100);
        }
    }

    const evaluarR3 = async (aprobado) => {
        const R3 = await businessLogic.validate_R3(id, aprobado);
        if (R3.data === true) {
            alert('Requerimiento 3 aprobado');
            setRequerimientoActual(4); // Assuming there's a next step after Requerimiento 2
        } else {
            alert('Requerimiento 3 rechazado');
            setRequerimientoActual(100);
        }
    }

    const evaluarR4 = async () => {
        const R4 = await businessLogic.validate_R4(id,ingresoCliente ,deudaCliente);       
        console.log(R4);
        if (R4.data === true) {
            alert('Requerimiento 4 aprobado');
            setRequerimientoActual(5);
        }
        else {
            alert('Requerimiento 4 rechazado');
            setRequerimientoActual(100);
        }
    }

    const evaluarR5 = async () => {
        const R5 = await businessLogic.validate_R5(id,valorPropiedad);       
        console.log(R5);
        if (R5.data === true) {
            alert('Requerimiento 5 aprobado');
            setRequerimientoActual(6);
        }
        else {
            alert('Requerimiento 5 rechazado');
            setRequerimientoActual(100);
        }
    }

    const evaluarR6 = async () => {
        const R6 = await businessLogic.validate_R6(id,edadCliente);       
        console.log(R6);
        if (R6.data === true) {
            alert('Requerimiento 6 aprobado');
            setRequerimientoActual(71);
        }
        else {
            alert('Requerimiento 6 rechazado');
            setRequerimientoActual(100);
        }
    }

    const evaluarR71 = async () => {
        const R71 = await businessLogic.validate_R71(id,saldoCuenta);       
        console.log(R71);
        if (R71.data === true) {
            alert('Requerimiento 7.1 aprobado');            
        }
        else {
            alert('Requerimiento 7.1 rechazado');
        }
        setRequerimientoActual(72);
    }

    const evaluarR72 = async () => {

        const R72 = await businessLogic.validate_R72(id,saldoCuenta,mes_1, mes_2, mes_3, mes_4, mes_5, mes_6, mes_7, mes_8, mes_9, mes_10, mes_11, mes_12);       
        console.log(R72);
        if (R72.data === true) {
            alert('Requerimiento 7.2 aprobado');            
        }
        else {
            alert('Requerimiento 7.2 rechazado');
        }
        setRequerimientoActual(73);
    }

    const evaluarR73 = async () => {

        const R73 = await businessLogic.validate_R73(id,ingresoCliente,mes_1_ret, mes_2_ret, mes_3_ret, mes_4_ret, mes_5_ret, mes_6_ret, mes_7_ret, mes_8_ret, mes_9_ret, mes_10_ret, mes_11_ret, mes_12_ret);       
        console.log(R73);
        if (R73.data === true) {
            alert('Requerimiento 7.2 aprobado');            
        }
        else {
            alert('Requerimiento 7.2 rechazado');
        }
        setRequerimientoActual(74);
    }

    const evaluarR74 = async () => {
        const R74 = await businessLogic.validate_R74(id,antiguedadCliente,saldoCuenta);       
        console.log(R74);
        if (R74.data === true) {
            alert('Requerimiento 7.4 aprobado');            
        }
        else {
            alert('Requerimiento 7.4 rechazado');
        }
        setRequerimientoActual(75);
    }

    const evaluarR75 = async () => {

        const R75 = await businessLogic.validate_R75(id,saldoCuenta, mes_7, mes_8, mes_9, mes_10, mes_11, mes_12);       
        console.log(R75);
        if (R75.data === true) {
            alert('Requerimiento 7.5 aprobado');            
        }
        else {
            alert('Requerimiento 7.5 rechazado');
        }
        
        setRequerimientoActual(100);
        
    }

    
    const getRequeriments = async () => {

        try {
            const response = await businessLogic.validate_R7(id);
            console.log(response);
        }
        catch (error) {
            console.error('Error fetching data:', error);
        }

        try {
            const response = await creditEvaluation.getEvaluacionCredit(id);

            setDataRequerimientos(response.data);
            console.log(response.data);
            setRequerimientoActual(100);


        } catch (error) {
            console.error('Error fetching credit evaluation:', error);
        }

        try {
            const response = await businessLogic.evaluacionSolicitud(id);
            console.log(response.data);
            setEvaluacionSolicitud(response.data);
        }
        catch (error) {
            console.error('Error fetching data:', error);
        }

        
        setRequerimientoActual(100);
        
    }

    useEffect(() => {
        if (requerimientoActual === 100) {
            getRequeriments();
        }
    }, [requerimientoActual]);

    const handleCreditRequest = () => {
        navigate('/solicitudes-credito');
    };

    
    return (
        <div>
            <h1 style={{ fontSize: '2em' }}>Evaluación de Requerimientos</h1>

            {requerimientoActual === 1 && (
                <div>
                    <p style={{ fontSize: '1.5em' }}>Requerimiento 1</p>
                    <p style={{ fontSize: '1em' }}>Relación Cuota/Ingreso</p>
                    <label style={{ fontSize: '1.2em' }}>
                        Ingreso Cliente:
                        <input 
                            type="integer" 
                            value={ingresoCliente} 
                            onChange={(e) => setIngresoCliente(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <div style={{ marginTop: '10px' }}>
                        <button onClick={() => evaluarR1()} style={{ fontSize: '1em' }}>Evaluar Requerimiento 1</button>
                    </div>
                </div>
            )}

            {requerimientoActual === 2 && (
                <div>
                    <p style={{ fontSize: '1.5em' }}>Requerimiento 2</p>
                    <p style={{ fontSize: '1em' }}>Historial Crediticio del Cliente</p>
                    
                    <div style={{ marginTop: '10px' }}>
                        <button 
                            onClick={() => evaluarR2(false)} 
                            style={{ fontSize: '1em', backgroundColor: 'red', color: 'white' }}
                        >
                            Rechazar
                        </button>
                        
                        <button 
                            onClick={() => evaluarR2(true)} 
                            style={{ fontSize: '1em', backgroundColor: 'green', color: 'white', marginRight: '10px' }}
                        >
                            Aprobar
                        </button>
                        
                    </div>
                </div>
            )}

            {requerimientoActual === 3 && (
                <div>
                    <p style={{ fontSize: '1.5em' }}>Requerimiento 3</p>
                    <p style={{ fontSize: '1em' }}>Antigüedad Laboral y Estabilidad</p>
                    
                    <div style={{ marginTop: '10px' }}>
                        <button 
                            onClick={() => evaluarR3(false)} 
                            style={{ fontSize: '1em', backgroundColor: 'red', color: 'white' }}
                        >
                            Rechazar
                        </button>
                        
                        <button 
                            onClick={() => evaluarR3(true)} 
                            style={{ fontSize: '1em', backgroundColor: 'green', color: 'white', marginRight: '10px' }}
                        >
                            Aprobar
                        </button>
                        
                    </div>
                </div>
            )}  

            {requerimientoActual === 4 && (
                <div>
                    <p style={{ fontSize: '1.5em' }}>Requerimiento 4</p>
                    <p style={{ fontSize: '1em' }}>Relación Deuda/Ingreso</p>
                    <label style={{ fontSize: '1.2em' }}>
                        Deudas CLiente:
                        <input 
                            type="integer" 
                            value={deudaCliente} 
                            onChange={(e) => setDeudaCliente(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <div style={{ marginTop: '10px' }}>
                        <button onClick={() => evaluarR4()} style={{ fontSize: '1em' }}>Evaluar Requerimiento 4</button>
                    </div>
                </div>
            )}

            {requerimientoActual === 5 && (
                <div>
                    <p style={{ fontSize: '1.5em' }}>Requerimiento 5</p>
                    <p style={{ fontSize: '1em' }}>Monto Máximo de Financiamiento</p>
                    <label style={{ fontSize: '1.2em' }}>
                        Valor Propiedad:
                        <input 
                            type="integer" 
                            value={valorPropiedad} 
                            onChange={(e) => setValorPropiedad(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <div style={{ marginTop: '10px' }}>
                        <button onClick={() => evaluarR5()} style={{ fontSize: '1em' }}>Evaluar Requerimiento 5</button>
                    </div>
                </div>
            )}

            {requerimientoActual === 6 && (
                <div>
                    <p style={{ fontSize: '1.5em' }}>Requerimiento 6</p>
                    <p style={{ fontSize: '1em' }}>Edad del Solicitante</p>
                    <label style={{ fontSize: '1.2em' }}>
                        Edad Cliente:
                        <input 
                            type="integer" 
                            value={edadCliente} 
                            onChange={(e) => setEdadCliente(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <div style={{ marginTop: '10px' }}>
                        <button onClick={() => evaluarR6()} style={{ fontSize: '1em' }}>Evaluar Requerimiento 6</button>
                    </div>
                </div>
            )}


            {requerimientoActual === 71 && (
                <div>
                    <p style={{ fontSize: '1.5em', marginTop: '50px' }}>Requerimiento 7 Capacidad de Ahorro</p>
                    <p style={{ fontSize: '1em' }}>1- Saldo Mínimo Requerido</p>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Saldo Cuenta:
                        <input 
                            type="integer" 
                            value={saldoCuenta} 
                            onChange={(e) => setSaldoCuenta(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
       
                    <div style={{ marginTop: '10px' }}>
                        <button onClick={() => evaluarR71()} style={{ fontSize: '1em' }}>Evaluar Requerimiento 7.1</button>
                    </div>
                </div>
            )}


            {requerimientoActual === 72 && (
                <div>
                    <p style={{ fontSize: '1.5em', marginTop: '50px' }}>Requerimiento 7 Capacidad de Ahorro</p>
                    <p style={{ fontSize: '1em' }}>2- Historial de Ahorro Consistente</p>
                    
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Retiro Mes 1:
                        <input 
                            type="integer" 
                            value={mes_1} 
                            onChange={(e) => setMes_1(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Retiro Mes 2:
                        <input 
                            type="integer" 
                            value={mes_2} 
                            onChange={(e) => setMes_2(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Retiro Mes 3:
                        <input 
                            type="integer" 
                            value={mes_3} 
                            onChange={(e) => setMes_3(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Retiro Mes 4:
                        <input 
                            type="integer" 
                            value={mes_4} 
                            onChange={(e) => setMes_4(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Retiro Mes 5:
                        <input 
                            type="integer" 
                            value={mes_5} 
                            onChange={(e) => setMes_5(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Retiro Mes 6:
                        <input 
                            type="integer" 
                            value={mes_6} 
                            onChange={(e) => setMes_6(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Retiro Mes 7:
                        <input 
                            type="integer" 
                            value={mes_7} 
                            onChange={(e) => setMes_7(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Retiro Mes 8:
                        <input 
                            type="integer" 
                            value={mes_8} 
                            onChange={(e) => setMes_8(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Retiro Mes 9:
                        <input 
                            type="integer" 
                            value={mes_9} 
                            onChange={(e) => setMes_9(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Retiro Mes 10:
                        <input 
                            type="integer" 
                            value={mes_10} 
                            onChange={(e) => setMes_10(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Retiro Mes 11:
                        <input 
                            type="integer" 
                            value={mes_11} 
                            onChange={(e) => setMes_11(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Retiro Mes 12:
                        <input 
                            type="integer" 
                            value={mes_12} 
                            onChange={(e) => setMes_12(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>


                    <div style={{ marginTop: '10px' }}>
                        <button onClick={() => evaluarR72()} style={{ fontSize: '1em' }}>Evaluar Requerimiento 7.2</button>
                    </div>
                </div>
            )}

            {requerimientoActual === 73 && (
                <div>
                    <p style={{ fontSize: '1.5em', marginTop: '50px' }}>Requerimiento 7 Capacidad de Ahorro</p>
                    <p style={{ fontSize: '1em' }}>3- Depósitos Periódicos</p>
                    
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Deposito Mes 1:
                        <input 
                            type="integer" 
                            value={mes_1_ret} 
                            onChange={(e) => setMes_1_ret(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Deposito Mes 2:
                        <input 
                            type="integer" 
                            value={mes_2_ret} 
                            onChange={(e) => setMes_2_ret(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Deposito Mes 3:
                        <input 
                            type="integer" 
                            value={mes_3_ret} 
                            onChange={(e) => setMes_3_ret(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Deposito Mes 4:
                        <input 
                            type="integer" 
                            value={mes_4_ret} 
                            onChange={(e) => setMes_4_ret(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Deposito Mes 5:
                        <input 
                            type="integer" 
                            value={mes_5_ret} 
                            onChange={(e) => setMes_5_ret(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Deposito Mes 6:
                        <input 
                            type="integer" 
                            value={mes_6_ret} 
                            onChange={(e) => setMes_6_ret(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Deposito Mes 7:
                        <input 
                            type="integer" 
                            value={mes_7_ret} 
                            onChange={(e) => setMes_7_ret(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Deposito Mes 8:
                        <input 
                            type="integer" 
                            value={mes_8_ret} 
                            onChange={(e) => setMes_8_ret(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Deposito Mes 9:
                        <input 
                            type="integer" 
                            value={mes_9_ret} 
                            onChange={(e) => setMes_9_ret(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Deposito Mes 10:
                        <input 
                            type="integer" 
                            value={mes_10_ret} 
                            onChange={(e) => setMes_10_ret(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Deposito Mes 11:
                        <input 
                            type="integer" 
                            value={mes_11_ret} 
                            onChange={(e) => setMes_11_ret(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Deposito Mes 12:
                        <input 
                            type="integer" 
                            value={mes_12_ret} 
                            onChange={(e) => setMes_12_ret(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>


                    <div style={{ marginTop: '10px' }}>
                        <button onClick={() => evaluarR73()} style={{ fontSize: '1em' }}>Evaluar Requerimiento 7.3</button>
                    </div>
                </div>
            )}

            {requerimientoActual === 74 && (
                <div>
                    <p style={{ fontSize: '1.5em', marginTop: '50px' }}>Requerimiento 7 Capacidad de Ahorro</p>
                    <p style={{ fontSize: '1em' }}>4- Relación Saldo/Años de Antigüedad</p>
                    <label style={{ fontSize: '1.2em', display: 'block', marginBottom: '10px' }}>
                        Antiguedad Cliente:
                        <input 
                            type="integer" 
                            value={antiguedadCliente} 
                            onChange={(e) => setAntiguedadCliente(e.target.value)} 
                            style={{ fontSize: '1em' }}
                        />
                    </label>
       
                    <div style={{ marginTop: '10px' }}>
                        <button onClick={() => evaluarR74()} style={{ fontSize: '1em' }}>Evaluar Requerimiento 7.4</button>
                    </div>
                </div>
            )}

            {requerimientoActual === 75 && (
                <div>
                    <p style={{ fontSize: '1.5em', marginTop: '50px' }}>Requerimiento 7 Capacidad de Ahorro</p>
                    <p style={{ fontSize: '1em' }}>5- Retiros Recientes</p>
                    
       
                    <div style={{ marginTop: '10px' }}>
                        <button onClick={() => evaluarR75()} style={{ fontSize: '1em' }}>Evaluar Requerimiento 7.5</button>
                    </div>
                </div>
            )}

            {requerimientoActual === 100 && (
                <div>
                    <h2 style={{ fontSize: '1.5em', marginTop: '50px' }}>Estado final de Requerimientos</h2>
                    <ul style={{ fontSize: '1em' }}>
                        <li>Requerimiento 1: {dataRequerimientos?.r1 ? 'Aprobado' : 'Rechazado'}</li>
                        <li>Requerimiento 2: {dataRequerimientos?.r2 ? 'Aprobado' : 'Rechazado'}</li>
                        <li>Requerimiento 3: {dataRequerimientos?.r3 ? 'Aprobado' : 'Rechazado'}</li>
                        <li>Requerimiento 4: {dataRequerimientos?.r4 ? 'Aprobado' : 'Rechazado'}</li>
                        <li>Requerimiento 5: {dataRequerimientos?.r5 ? 'Aprobado' : 'Rechazado'}</li>
                        <li>Requerimiento 6: {dataRequerimientos?.r6 ? 'Aprobado' : 'Rechazado'}</li>
                        <li>Requerimiento 7: {dataRequerimientos?.r7 || "Sin información"}</li>
                        <li style={{ marginLeft: '200px' }}>Requerimiento 7.1: {dataRequerimientos?.r71 ? 'Aprobado' : 'Rechazado'}</li>
                        <li style={{ marginLeft: '200px' }}>Requerimiento 7.2: {dataRequerimientos?.r72 ? 'Aprobado' : 'Rechazado'}</li>
                        <li style={{ marginLeft: '200px' }}>Requerimiento 7.3: {dataRequerimientos?.r73 ? 'Aprobado' : 'Rechazado'}</li>
                        <li style={{ marginLeft: '200px' }}>Requerimiento 7.4: {dataRequerimientos?.r74 ? 'Aprobado' : 'Rechazado'}</li>
                        <li style={{ marginLeft: '200px' }}>Requerimiento 7.5: {dataRequerimientos?.r75 ? 'Aprobado' : 'Rechazado'}</li>
                    </ul>                   

                    <h2 style={{ fontSize: '1.5em', marginTop: '50px' }}>Evaluación de Solicitud</h2>
                    <p style={{ fontSize: '1em' }}>Resultado: {evaluacionSolicitud}</p>

                    <button onClick={() => handleCreditRequest() } style={{ fontSize: '1em', marginTop: '20px' }}>
                        Finalizar Evaluación
                    </button>
                    
                </div>
            )}




        </div>
    );
};

export default RequirementsEvaluation;