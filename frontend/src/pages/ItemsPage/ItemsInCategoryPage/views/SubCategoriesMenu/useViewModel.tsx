import {useAppSelector} from "../../../../../redux/Hooks";
import CategoriesSelector from "../../../../../redux/categories/CategoriesSelector"
import useFetchCategory from "../../../../../data-sources/useFetchCategory";
import useFetchItems from "../../../../../data-sources/useFetchItems";

const useViewModel = ()=>{
    const { data } = useAppSelector(CategoriesSelector).one

    const fetchCategory = useFetchCategory()
    const fetchItems = useFetchItems()

    const onClick = (selfUrl: string, itemsUrl: string)=>()=>{
        fetchCategory(selfUrl)
        fetchItems(itemsUrl)
    }
    return {
        data,
        onClick
    }
}
export default useViewModel