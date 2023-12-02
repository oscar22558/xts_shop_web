import qs from "qs"
import { ApiRequestWithoutToken } from "../ApiRequest"

type GetCartItemsRequest = {
    params: {
        ids: number[]
    }
}

const GetItemsByIdApi = (request: GetCartItemsRequest)=>ApiRequestWithoutToken({
    url: "items",
    ...request,
    paramsSerializer: (params: any) => qs.stringify(params, {arrayFormat: "brackets"})
})
export default GetItemsByIdApi