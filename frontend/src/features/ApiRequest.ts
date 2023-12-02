import axios from "axios"
import ApiConfig from "./ApiConfig"

const axiosInstance = axios.create(ApiConfig)
export const ApiRequestWithoutToken = axios.create(ApiConfig)

export const setAuthorizationHeader = (token: string)=>{
    axiosInstance.defaults.headers.common["Authorization"] = token == "" ? "" : `Bearer ${token}`
}

export default axiosInstance