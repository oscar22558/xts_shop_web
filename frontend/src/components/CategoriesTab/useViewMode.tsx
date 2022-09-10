import useFetchCategory from "../../data-sources/category/useFetchCategory"
import useFetchItems from "../../data-sources/items/useFetchItems"

const useViewModel = ()=>{
    const fetchItems = useFetchItems()
    const fetchCategory = useFetchCategory()
    return {
        handleItemClick: (categoryId: number)=>{
            fetchCategory(categoryId)
            fetchItems(categoryId)
        }
    }
}
export default useViewModel