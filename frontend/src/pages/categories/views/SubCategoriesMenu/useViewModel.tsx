import {useAppSelector} from "../../../../redux/hooks";
import selector from "../../../../redux/categories/selector"
import useFetchCategory from "../../../../dataSources/useFetchCategory";
import useFetchItems from "../../../../dataSources/useFetchItems";

const useViewModel = ()=>{
    const { data } = useAppSelector(selector).one
    const fetchCategory = useFetchCategory()
    const fetchItems = useFetchItems()
    const onClick = (selfUrl: string, itemsUrl: string)=>()=>{
        fetchCategory(selfUrl)
        fetchItems()(itemsUrl)
    }
    return {
        data,
        onClick
    }
}
export default useViewModel