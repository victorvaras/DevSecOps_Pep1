import httpClient from "../http-common";

const getLoanType = id => {
    return httpClient.get(`/tipo_prestamo/${id}`);
}


export default { getLoanType };