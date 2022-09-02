import { useEffect, useState } from "react"
import useFetchItems from "../../../../../data-sources/items/useFetchItems"
import brandsSelector from "../../../../../features/brands/BrandsSelector"
import { useAppDispatch, useAppSelector } from "../../../../../features/Hooks"
import itemActions from "../../../../../features/items/ItemsAction"
import categorySelector from "../../../../../features/categories/CategoriesSelector"
const useViewModel = ()=>{
    const [checkBoxStates, setCheckBoxStates] = useState(getInitStates(5))
    const {allBrands} = useAppSelector(brandsSelector)
    const checkBoxModelList = allBrands.data
    const dispatch = useAppDispatch()
    const updateBrandFilterItemAction = itemActions.setFetchItemOptions.brandFilter    
    const getItemsUnderCategoryUrl = useAppSelector(categorySelector)
        ?.getCategoryResponse?.data
        ?._links?.items.href
    const fetchItems = useFetchItems()

    const getCheckBoxViewModels =  ()=>{
        const limit = 5
        return checkBoxModelList
            .filter((brand, index)=>index <= limit-1)
            .map((brand, index)=>({
                label: brand.name,
                state: checkBoxStates[index],
                setState: checkboxHandleStateChange
            }))
    }

    const checkboxHandleStateChange =  (newState: boolean, index: number)=>{
        const newStates = checkBoxStates.map((oldState, oldeStateIndex)=>oldeStateIndex === index ? newState: oldState)
        setCheckBoxStates(newStates)
    }
    
    const updateItemApiBrandFilter = ()=>{
        const selectedBrandIds = checkBoxStates
            .map((state, index)=>state ? checkBoxModelList[index].id : -1)
            .filter((brandId)=>brandId != -1)
        dispatch(updateBrandFilterItemAction({brandIds: selectedBrandIds}))
        getItemsUnderCategoryUrl && fetchItems(getItemsUnderCategoryUrl)
    }

    useEffect(updateItemApiBrandFilter, [checkBoxStates])

    return getCheckBoxViewModels()

}

const getInitStates = (size: number)=>{
    const initStates = []
    for(let i = 0; i < size; i++){
        initStates.push(false)
    }
    return initStates
}
export default useViewModel