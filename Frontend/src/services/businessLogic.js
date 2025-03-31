import httpClient from "../http-common";

const validate_R1 = (ingresos, id) => {
    return httpClient.get(`/business_logic/validate_r1/${ingresos}/${id}`);
}

const validate_R2 = (id, requisito) => {
    return httpClient.get(`/business_logic/validate_r2/${id}/${requisito}`);
}

const validate_R3 = (id, requisito) => {
    return httpClient.get(`/business_logic/validate_r3/${id}/${requisito}`);
}

const validate_R4 = (id, ingreso, deuda) => {
    return httpClient.get(`/business_logic/validate_r4/${id}/${ingreso}/${deuda}`);
}

const validate_R5 = (id, valorPropiedad) => {
    return httpClient.get(`/business_logic/validate_r5/${id}/${valorPropiedad}`);
}

const validate_R6 = (id, edadCliente) => {
    return httpClient.get(`/business_logic/validate_r6/${id}/${edadCliente}`);
}

const validate_R71 = (id, saldoCuenta) => {
    return httpClient.get(`/business_logic/validate_r71/${id}/${saldoCuenta}`);
}

const validate_R72 = (id, saldoCuenta,mes_1, mes_2, mes_3, mes_4, mes_5, mes_6, mes_7, mes_8, mes_9, mes_10, mes_11, mes_12) => {
    return httpClient.get(`/business_logic/validate_r72/${id}/${saldoCuenta}/${mes_1}/${mes_2}/${mes_3}/${mes_4}/${mes_5}/${mes_6}/${mes_7}/${mes_8}/${mes_9}/${mes_10}/${mes_11}/${mes_12}`);
    
}

const validate_R73 = (id, ingresoCliente,mes_1, mes_2, mes_3, mes_4, mes_5, mes_6, mes_7, mes_8, mes_9, mes_10, mes_11, mes_12) => {
    return httpClient.get(`/business_logic/validate_r73/${id}/${ingresoCliente}/${mes_1}/${mes_2}/${mes_3}/${mes_4}/${mes_5}/${mes_6}/${mes_7}/${mes_8}/${mes_9}/${mes_10}/${mes_11}/${mes_12}`);
    
}

const validate_R74 = (id, antiguedad, saldoCuenta) => {
    return httpClient.get(`/business_logic/validate_r74/${id}/${antiguedad}/${saldoCuenta}`);
}

const validate_R75 = (id, saldo_cuenta, mes_7, mes_8, mes_9, mes_10, mes_11, mes_12) => {
    return httpClient.get(`/business_logic/validate_r75/${id}/${saldo_cuenta}/${mes_7}/${mes_8}/${mes_9}/${mes_10}/${mes_11}/${mes_12}`);
    
}

const validate_R7 = (id) => {
    return httpClient.get(`/business_logic/validate_r7/${id}`);
}


const evaluacionSolicitud = (id) => {
    return httpClient.get(`/business_logic/validarEvaluacionCredito/${id}`);
}

const costo_total_credito = (id) => {
    return httpClient.get(`/business_logic/costo_total_credito/${id}`);
}

export default { validate_R1, validate_R2, validate_R3, validate_R4,validate_R5, validate_R6, validate_R71, validate_R72, validate_R73,
                validate_R74, validate_R75, validate_R7, evaluacionSolicitud, costo_total_credito
 };
