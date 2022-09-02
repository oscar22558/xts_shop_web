import axiosInstance from "../ApiRequest"
import qs from "qs"

type GetCartItemsRequest = {
    url: string,
    params: {
        ids: number[]
    }
}

const GetItemsByIdApi = (request: GetCartItemsRequest)=>axiosInstance({
    ...request,
    paramsSerializer: (params: any) => qs.stringify(params, {arrayFormat: "brackets"})
})
export default GetItemsByIdApi