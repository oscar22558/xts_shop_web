import useFetchItems from "../../../../dataSources/useFetchItems"
import { useAppDispatch, useAppSelector } from "../../../../redux/hooks"
import selector from "../../../../redux/categories/selector"
import PriceFilter from "../../../../dataSources/dto/PriceFilter"
import itemsActions from "../../../../redux/items/action"

const useViewModel = ()=>{
    const getItemsUndeCategoryUrl = useAppSelector(selector)
        ?.one?.data
        ?._links?.items.href
    const fetchItems = useFetchItems()
    const dispatch = useAppDispatch()

    const handleSearchBtnClick = (priceFilter: PriceFilter)=>()=>{
        setPriceFilter(priceFilter)
        fetchItemsWithOptions()
    }

    const setPriceFilter = (priceFilter: PriceFilter)=>{
        dispatch(itemsActions.setFetchItemOptions.priceFilter(priceFilter))
    }

    const fetchItemsWithOptions = ()=>{
        getItemsUndeCategoryUrl &&
        fetchItems(getItemsUndeCategoryUrl)
    }

    return {
        handleSearchBtnClick
    }
}
export default useViewModel