import ApiConfig from "../ApiConfig";
import axios from "../axios";

const AuthenticationApi = (url: string)=>axios({
    ...ApiConfig,
    url,
    method: "POST"
})
export default AuthenticationApi