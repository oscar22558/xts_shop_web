import axios from "axios"
import ApiConfig from "./ApiConfig"

const axiosInstance = axios.create(ApiConfig)

export const setAuthorizationHeader = (token: string)=>{
    axiosInstance.defaults.headers.common["Authorization"] = `Bearer ${token}`
}

export default axiosInstance