import axiosInstance from "../ApiRequest";
import qs from "qs"

export const ItemsApi = {
    getAll: {
        of: (getConfig: any)=>axiosInstance({
            ...getConfig,
            paramsSerializer: (params: any) => qs.stringify(params, {arrayFormat: "brackets"})
        })
    },
}
export default ItemsApi