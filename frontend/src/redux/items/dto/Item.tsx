import Link from "../../dto/Link";
import Model from "../../dto/Model";
import PriceHistory from "./PriceHistory";

export type Item = {
    id: number
    name: string
    price: PriceHistory
    imgUrl: string
    manufacturer: string
    stock: number
    _links: {
        create: Link
        update: Link
        updateImage: Link
        delete: Link
    }
} & Model
export default Item