import qs from "qs"
import { ApiRequestWithoutToken } from "../ApiRequest"

type GetCartItemsRequest = {
    url: string,
    params: {
        ids: number[]
    }
}

const GetItemsByIdApi = (request: GetCartItemsRequest)=>ApiRequestWithoutToken({
    ...request,
    paramsSerializer: (params: any) => qs.stringify(params, {arrayFormat: "brackets"})
})
export default GetItemsByIdApi