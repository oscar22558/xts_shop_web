import {useAppSelector} from "../../../../redux/hooks";
import selector from "../../../../redux/categories/selector"
import {Category} from "../../../../redux/categories/reducer";
const useViewModel = ()=>{
    const { data: selectedCategory } = useAppSelector(selector).one
    const { data: categories } = useAppSelector(selector).all
    return findPath({
        id: -1,
        name: "",
        _links: {
            self: { href: ""},
            categories: { href: ""}
        },
        subCategories: categories,
        items: []
    }, selectedCategory?.id ?? 0)
        ?.reduce((acc, current, index)=> !acc ? current : `${acc} > ${current}`)
        ?? ""
}
const findPath = (root: Category, id: number): string[] | null =>{
    if(root.id === id) return [root.name]
    for (const node of root.subCategories) {
        const target = findPath(node, id)
        if(target != null){
            return [root.name, ...target]
        }
    }
    return null
}
export default useViewModel