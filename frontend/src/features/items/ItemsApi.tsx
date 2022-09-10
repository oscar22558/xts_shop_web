import { ApiRequestWithoutToken } from "../ApiRequest";
import qs from "qs"

export const ItemsApi = {
    getAll: {
        of: (getRequestConfig: any)=>ApiRequestWithoutToken({
            ...getRequestConfig,
            paramsSerializer: (params: any) => qs.stringify(params, {arrayFormat: "brackets"})
        })
    },
}
export default ItemsApi