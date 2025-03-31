import httpClient from "../http-common";

const createClient = (data) => {
    return httpClient.post("/cliente/nuevo", data, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    });
};

const loginClient = (data) => {
    return httpClient.post("/cliente/login", data);
};

const getClient = id => {
    return httpClient.get(`/cliente/${id}`);
}

export default { createClient, loginClient, getClient };