import {useAppSelector} from "../../../../../../../features/Hooks";
import CategoriesSelector from "../../../../../../../features/categories/CategoriesSelector"
import useFetchCategory from "../../../../../../../data-sources/category/useFetchCategory";
import useFetchItems from "../../../../../../../data-sources/items/useFetchItems";

const useViewModel = (index: number)=>{
    const { data } = useAppSelector(CategoriesSelector).getCategoryResponse
    const fetchCategory = useFetchCategory()
    const fetchItems = useFetchItems()
    const onClick = (selfUrl: string, itemsUrl: string)=>()=>{
        fetchCategory(selfUrl)
        fetchItems(itemsUrl)
    }
    return {
        categories: data?.subCategories?.[index].subCategories,
        onClick
    }
}
export default useViewModel