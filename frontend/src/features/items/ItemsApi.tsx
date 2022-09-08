import { ApiRequestWithoutToken } from "../ApiRequest";
import qs from "qs"

export const ItemsApi = {
    getAll: {
        of: (getConfig: any)=>ApiRequestWithoutToken({
            ...getConfig,
            paramsSerializer: (params: any) => qs.stringify(params, {arrayFormat: "brackets"})
        })
    },
}
export default ItemsApi