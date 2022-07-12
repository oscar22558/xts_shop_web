import { useEffect } from "react"
import useFetchItems from "../../../../../data-sources/useFetchItems"
import { useAppDispatch, useAppSelector } from "../../../../../redux/Hooks"
import CategoriesSelector from "../../../../../redux/categories/CategoriesSelector"
import PriceFilter from "../../../../../data-sources/models/PriceFilter"
import itemsActions from "../../../../../redux/items/ItemsAction"

const useViewModel = ()=>{
    const getItemsUndeCategoryUrl = useAppSelector(CategoriesSelector)
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
    useEffect(()=>{
        dispatch(itemsActions.setFetchItemOptions.priceFilter({maxPrice: 10000, minPrice: 0}))
    }, [])
    return {
        handleSearchBtnClick
    }
}
export default useViewModel