import Model from "./Model";

export type Category = {
    id: number
    name: string
    subCategories: Category[]
    _links: {
        items: { href: string }
    }
} & Model
export default Category
