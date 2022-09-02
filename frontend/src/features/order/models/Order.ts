import Invoice from "./Invoice"
import OrderItem from "./OrderItem"
import ShippingAddress from "./ShippingAddress"

type Order = {
    id: number
    items: OrderItem[]
    shippingAddress: ShippingAddress
    orderStatus: string
    orderPlaced: string
    invoice: Invoice
}
export default Order