import FormControl from "@mui/material/FormControl"
import MenuItem from "@mui/material/MenuItem"
import Select, { SelectChangeEvent } from "@mui/material/Select"
import { useEffect, useState } from "react"
import { useAppDispatch, useAppSelector } from "../../../../../features/Hooks"
import SortingOption from "./SortingOption"
import StyledInputBase from "./StyledInputBase"
import ItemsAction from "../../../../../features/items/ItemsAction"
import useFetchItems from "../../../../../data-sources/items/useFetchItems"
import categorySelector from "../../../../../features/categories/CategoriesSelector"
const ItemsSortingOption = ()=>{
    const [value, setValue] = useState<string>(SortingOption.Latest+"")
    const dispatch = useAppDispatch()
    const fetchItems = useFetchItems()
    const categoryId = useAppSelector(categorySelector).getCategoryResponse.data?.id
    const onChange = (event: SelectChangeEvent)=>{
        setValue(event.target.value+"")
        setRequestItemSorting(event.target.value)
        categoryId && fetchItems(categoryId)
    }
    const setRequestItemSorting = (selectedOption: string)=>{
        const method = selectedOption === SortingOption.Latest+"" ? "latest" : "price"
        const order = selectedOption === SortingOption.PriceDesc+"" ? "desc" : "asc"
        dispatch(ItemsAction.setFetchItemOptions.sorting({method, order}))
    }

    useEffect(()=>{
        dispatch(ItemsAction.setFetchItemOptions.sorting({method: "latest", order: "asc"}))
    }, [dispatch])

    return (
        <FormControl size="small">
            <Select 
                value={value} 
                onChange={onChange}
                input={<StyledInputBase />}
            >
                <MenuItem value={SortingOption.Latest+""}>Latest</MenuItem>
                <MenuItem value={SortingOption.PriceAsc+""}>Price(Asc)</MenuItem>
                <MenuItem value={SortingOption.PriceDesc+""}>Price(Desc)</MenuItem>
        
            </Select>
        </FormControl>
    )
}
export default ItemsSortingOption