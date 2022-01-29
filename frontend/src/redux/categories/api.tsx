import axios from "../axios";
import apiConfig from "../apiConfig";

export const api = {
    getAll: (url: string)=>axios({
        ...apiConfig,
        url
    }),
    get: (url: string)=>axios({
        ...apiConfig,
        url,
    })
}
export default api