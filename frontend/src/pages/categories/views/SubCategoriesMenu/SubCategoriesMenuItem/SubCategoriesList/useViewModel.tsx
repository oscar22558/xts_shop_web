import {useAppSelector} from "../../../../../../redux/hooks";
import selector from "../../../../../../redux/categories/selector"
import useFetchCategory from "../../../../../../dataSources/useFetchCategory";
import useFetchItems from "../../../../../../dataSources/useFetchItems";

const useViewModel = (index: number)=>{
    const { data } = useAppSelector(selector).one
    const fetchCategory = useFetchCategory()
    const fetchItems = useFetchItems()
    const onClick = (selfUrl: string, itemsUrl: string)=>()=>{
        fetchCategory(selfUrl)
        fetchItems()(itemsUrl)
    }
    return {
        categories: data?.subCategories?.[index].subCategories,
        onClick
    }
}
export default useViewModel