import { ApiRequestWithoutToken } from "../ApiRequest";

const ItemDetailApi = (itemId: number)=>ApiRequestWithoutToken({
    url: `/items/${itemId}`,
    method: "GET"
})
export default ItemDetailApi