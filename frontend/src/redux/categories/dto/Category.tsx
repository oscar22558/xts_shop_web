import Model from "../../dto/Model";
import Link from "../../dto/Link"
import Item from "../../items/dto/Item";
export type Category = {
    id: number
    name: string
    subCategories: Category[]
    _links: {
        create: Link,
        update: Link,
        delete: Link,
        all: Link,
        items: Link,
        createItems: Link,
    },
    items: Item[]
} & Model
export default Category
