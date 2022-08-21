import OrderItem from "./OrderItem"
import ShippingAddress from "./ShippingAddress"

type Order = {
    id: number
    items: OrderItem[]
    shippingAddress: ShippingAddress
    orderStatus: string
    orderPlaced: string
}
export default Order