import ApiRequest from "../ApiRequest";
import GetOrderListRequest from "./models/GetOrderListRequest";

const OrderApi = ({username}: GetOrderListRequest)=>ApiRequest({
    method: "GET",
    url: `/users/${username}/orders`
})
export default OrderApi