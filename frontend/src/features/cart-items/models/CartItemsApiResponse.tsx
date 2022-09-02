import Item from "../../items/models/Item"

type CartItemsApiResponse = {
    _embedded: {
        itemRepresentationModelList: Item[]
    }
}
export default CartItemsApiResponse