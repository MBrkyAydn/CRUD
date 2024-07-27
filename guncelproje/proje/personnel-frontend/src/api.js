import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8082/api',
});

export const getAllPersonnel = () => api.get('/personnel');
export const getPersonnelById = (id) => api.get(`/personnel/${id}`);
export const createPersonnel = (personnel) => api.post('/personnel', personnel);
export const updatePersonnel = (id, personnel) => api.put(`/personnel/${id}`, personnel);
export const deletePersonnel = (id) => api.delete(`/personnel/${id}`);

export default api;
