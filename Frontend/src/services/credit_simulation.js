import httpClient from "../http-common";

const createSimulacion = (data) => {
    return httpClient.post("/simulacion_credito/nuevo", data);
}


export default { createSimulacion };