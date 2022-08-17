import {useAppSelector} from "../../../../../redux/Hooks";
import CategoriesSelector from "../../../../../redux/categories/CategoriesSelector"
import {Category} from "../../../../../redux/categories/models/Category";
import CategoryStackItemModel from "./models/CategoryStackItemModel";

const useViewModel = ()=>{
    const { data: selectedCategory } = useAppSelector(CategoriesSelector).getCategoryResponse
    const { data: categories } = useAppSelector(CategoriesSelector).getAllCategoriesResponse
    return findPath({
        id: -1,
        name: "",
        _links: {
            items: {href: ""},
            self: { href: ""},
            all: { href: ""},
            createItems: { href: ""},
            delete: { href: ""},
            update: {href: ""},
            create: {href: ""}
        },
        subCategories: categories,
    }, selectedCategory?.id ?? 0)

}
const findPath = (root: Category, id: number): CategoryStackItemModel[] | null =>{
    if(root.id === id) return []
    for (const node of root.subCategories) {
        const target = findPath(node, id)
        if(target != null){
            return [{name: node.name, url: "/categories/"+ node.name, apiUrl: node._links.self.href, itemUrl: node._links.items.href}, ...target]
        }
    }
    return null
}
export default useViewModel