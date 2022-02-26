import axios from "../axios";
import apiConfig from "../apiConfig";

export const api = {
    get: ()=>axios({
        ...apiConfig,
        url: "/",
    })
}
export default api