import Item from "./Item"

type ItemModelListResponse = {
    _embedded?: {
        itemRepresentationModelList?: Item[]
    }
}
export default ItemModelListResponse