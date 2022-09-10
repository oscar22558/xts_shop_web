import Model from "../../models/Model";
import Link from "../../models/Link"
import Item from "../../items/models/Item";
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
} & Model
export default Category
