import axiosInstance from "../ApiRequest";
import AuthenticationRequest from "./models/AuthenticationRequest";

const AuthenticationApi = (requestConfig: {url: string, data: AuthenticationRequest})=>axiosInstance({
    ...requestConfig,
    method: "post"
})

export default AuthenticationApi