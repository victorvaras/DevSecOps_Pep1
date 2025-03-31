import httpClient from "../http-common";

const getSeguimientoSolicitud = id => {
    return httpClient.get(`/seguimiento_solicitud/${id}`);
}

export default { getSeguimientoSolicitud };