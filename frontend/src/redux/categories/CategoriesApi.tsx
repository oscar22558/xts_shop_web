import axios from "../axios";
import apiConfig from "../ApiConfig";

export const CategoriesApi = {
    getAll: (url: string)=>axios({
        ...apiConfig,
        url
    }),
    get: (url: string)=>axios({
        ...apiConfig,
        url,
    })
}
export default CategoriesApi