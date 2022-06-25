import useFetchItems from "../../../../dataSources/useFetchItems"
import { useAppSelector } from "../../../../redux/hooks"
import selector from "../../../../redux/categories/selector"
import PriceFilter from "../../../../dataSources/dto/PriceFilter"
const useViewModel = ()=>{
    const getItemsUndeCategoryUrl = useAppSelector(selector)
        ?.one?.data
        ?._links?.items.href
    const fetchItems = useFetchItems()
    return {
        setPriceFilter: (priceFilter: PriceFilter)=>()=>{
            getItemsUndeCategoryUrl &&
            fetchItems({
                filter: {
                    ...priceFilter,
                    brandIds: []
                }
            })(getItemsUndeCategoryUrl)
        }
    }
}
export default useViewModel