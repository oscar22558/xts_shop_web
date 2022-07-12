import axios from "../axios";
import apiConfig from "../ApiConfig";

export const ApiRoutesApi = {
    get: ()=>axios({
        ...apiConfig,
        url: "",
    })
}
export default ApiRoutesApi