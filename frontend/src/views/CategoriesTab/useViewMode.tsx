import useFetchCategory from "../../dataSources/useFetchCategory"
import useFetchItems from "../../dataSources/useFetchItems"

const useViewModel = ()=>{
    const fetchItems = useFetchItems()
    const fetchCategory = useFetchCategory()
    return {
        handleItemClick: (subCategoriesUrl: string, itemsUrl: string)=>{
            fetchItems(itemsUrl)
            fetchCategory(subCategoriesUrl)
        }
    }
}
export default useViewModel