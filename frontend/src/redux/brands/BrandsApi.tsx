import axios from "../axios";
import apiConfig from "../ApiConfig"

export const getAllBrandsApi = (url: string)=>axios({
    ...apiConfig,
    url,
})

