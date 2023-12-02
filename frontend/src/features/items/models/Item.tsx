import Link from "../../models/Link";
import Model from "../../models/Model";

export type Item = {
    id: number
    name: string
    description: string
    price: number
    imgUrl: string
    manufacturer: string
    brand: string
    _links: {
        create: Link
        update: Link
        updateImage: Link
        delete: Link
    }
} & Model
export default Item