import httpClient from "../http-common";

const getEvaluacionCredit = (id) => {
    return httpClient.get(`/evaluacion_credito/${id}`);  
}


export default { getEvaluacionCredit };