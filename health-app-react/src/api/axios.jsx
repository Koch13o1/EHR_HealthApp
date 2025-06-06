import axios from "axios";

const BASE_URL = "http://localhost:8085/api";

const axiosInstance = axios.create({
    baseURL: BASE_URL,
})

axiosInstance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("jwt");
        if (token){
            config.headers.Authorization = `Bearer ${token}`
        }
        return config
    },
    (error) => Promise.reject(error)
);

export default axiosInstance