import httpClient from "../http-common";

const createApllyCredit = (data) => {
    return httpClient.post("/solicitud_credito/nuevo", data, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    });
}

const getApplyCredit = () => {
    return httpClient.get("/solicitud_credito/");
}

const getApplyCreditById = (id) => {
    return httpClient.get(`/solicitud_credito/${id}`);
}

const putApplyCreditSeguimiento = (id_Credit, id_seguimiento) => {
    return httpClient.put(`/solicitud_credito/cambio_seguiminto_solicitud/${id_Credit}/${id_seguimiento}`);
}



export default { createApllyCredit, getApplyCredit, getApplyCreditById, putApplyCreditSeguimiento };