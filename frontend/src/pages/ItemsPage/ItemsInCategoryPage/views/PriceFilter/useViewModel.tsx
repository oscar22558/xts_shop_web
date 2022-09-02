import { useEffect } from "react"
import useFetchItems from "../../../../../data-sources/items/useFetchItems"
import { useAppDispatch, useAppSelector } from "../../../../../features/Hooks"
import CategoriesSelector from "../../../../../features/categories/CategoriesSelector"
import PriceFilter from "../../../../../data-sources/models/PriceFilter"
import itemsActions from "../../../../../features/items/ItemsAction"

const useViewModel = ()=>{
    const getItemsUndeCategoryUrl = useAppSelector(CategoriesSelector)
        ?.getCategoryResponse?.data
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