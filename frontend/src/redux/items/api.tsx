import axios from "../axios";
import apiConfig from "../apiConfig";

export const api = {
    getAll: {
        of: (url: string)=>axios({
            ...apiConfig,
            url
        })
    },
}
export default api