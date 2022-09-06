import axiosInstance from "../ApiRequest";
import AuthenticationRequest from "./models/AuthenticationRequest";

const AuthenticationApi = ( data: AuthenticationRequest)=>axiosInstance({
    method: "post",
    url: "/auth",
    data,
})

export default AuthenticationApi