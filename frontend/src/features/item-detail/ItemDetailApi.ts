import ApiRequest from "../ApiRequest";

const ItemDetailApi = (itemId: number)=>ApiRequest({
    url: `/items/${itemId}`,
    method: "GET"
})
export default ItemDetailApi