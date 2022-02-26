import Model from "./Model";

export type Item = {
    id: number
    name: string
    price: number
    imgUrl: string
    manufacturer: string
} & Model
export default Item