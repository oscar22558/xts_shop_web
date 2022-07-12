import axios from "../axios"
import ApiConfig from "../ApiConfig"
import qs from "qs"

type GetCartItemsRequest = {
    url: string,
    params: {
        itemIds: number[]
    }
}

const GetItemsByIdApi = (request: GetCartItemsRequest)=>axios({
    ...ApiConfig,
    ...request,
    paramsSerializer: (params: any) => qs.stringify(params, {arrayFormat: "brackets"})
})
export default GetItemsByIdApi