import ApiRequest from "../ApiRequest"

const GetOrderApi = (orderId: number)=>ApiRequest({
    method: "GET",
    url: `/orders/${orderId}` 
})
export default GetOrderApi