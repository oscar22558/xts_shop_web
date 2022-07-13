import useFetchCategory from "../../data-sources/category/useFetchCategory"
import useFetchItems from "../../data-sources/items/useFetchItems"

const useViewModel = ()=>{
    const fetchItems = useFetchItems()
    const fetchCategory = useFetchCategory()
    return {
        handleItemClick: (subCategoriesUrl: string, itemsUrl: string)=>{
            fetchCategory(subCategoriesUrl)
            fetchItems(itemsUrl)
        }
    }
}
export default useViewModel