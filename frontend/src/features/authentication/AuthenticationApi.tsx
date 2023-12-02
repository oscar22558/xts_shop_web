import ApiRequest, { ApiRequestWithoutToken } from "../ApiRequest";
import AuthenticationRequest from "./models/AuthenticationRequest";

const AuthenticationApi = ( data: AuthenticationRequest)=>ApiRequestWithoutToken({
    method: "post",
    url: "/auth",
    data,
})

export default AuthenticationApi