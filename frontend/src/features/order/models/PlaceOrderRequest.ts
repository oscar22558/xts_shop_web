import CreateAddressRequest from "./CreateAddressRequest"
import CreateOrderedItemRequest from "./CreateOrderedItemRequest"

type PlaceOrderRequest = {
    address: CreateAddressRequest
    items: CreateOrderedItemRequest[]
}
export default PlaceOrderRequest