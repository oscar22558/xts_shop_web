import OrderItem from "./OrderItem"
import ShippingAddress from "./ShippingAddress"

type Order = {
    id: number
    items: OrderItem[]
    shippingAddress: ShippingAddress
    orderStatus: string
}
export default Order