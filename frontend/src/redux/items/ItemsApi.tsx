import axios from "../axios";
import apiConfig from "../ApiConfig";
import qs from "qs"

export const ItemsApi = {
    getAll: {
        of: (getConfig: any)=>axios({
            ...apiConfig,
            ...getConfig,
            paramsSerializer: (params: any) => qs.stringify(params, {arrayFormat: "brackets"})
        })
    },
}
export default ItemsApi