import ApiConfig from "../ApiConfig";
import axios from "../axios";
import AuthenticationRequest from "./models/AuthenticationRequest";

const AuthenticationApi = (requestConfig: {url: string, data: AuthenticationRequest})=>axios({
    ...ApiConfig,
    ...requestConfig,
    method: "post"
})

export default AuthenticationApi