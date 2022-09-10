import {useAppSelector} from "../../../../../features/Hooks";
import CategoriesSelector from "../../../../../features/categories/CategoriesSelector"
import useFetchCategory from "../../../../../data-sources/category/useFetchCategory";
import useFetchItems from "../../../../../data-sources/items/useFetchItems";

const useViewModel = ()=>{
    const { data } = useAppSelector(CategoriesSelector).getCategoryResponse

    const fetchCategory = useFetchCategory()
    const fetchItems = useFetchItems()

    const onClick = (categoryId: number)=>()=>{
        fetchCategory(categoryId)
        fetchItems(categoryId)
    }
    return {
        data,
        onClick
    }
}
export default useViewModel