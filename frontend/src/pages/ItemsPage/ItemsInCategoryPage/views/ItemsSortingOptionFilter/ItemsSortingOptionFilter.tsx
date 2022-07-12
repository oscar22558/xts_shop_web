import FormControl from "@mui/material/FormControl"
import MenuItem from "@mui/material/MenuItem"
import Select, { SelectChangeEvent } from "@mui/material/Select"
import { useEffect, useState } from "react"
import { useAppDispatch, useAppSelector } from "../../../../../redux/Hooks"
import SortingOption from "./SortingOption"
import StyledInputBase from "./StyledInputBase"
import ItemsAction from "../../../../../redux/items/ItemsAction"
import useFetchItems from "../../../../../data-sources/useFetchItems"
import categorySelector from "../../../../../redux/categories/CategoriesSelector"
const ItemsSortingOption = ()=>{
    const [value, setValue] = useState<string>(SortingOption.Latest+"")
    const dispatch = useAppDispatch()
    const fetchItems = useFetchItems()
    const selectedCategoriesUrl = useAppSelector(categorySelector).one.data?._links.items.href
    const onChange = (event: SelectChangeEvent)=>{
        setValue(event.target.value+"")
        setRequestItemSorting(event.target.value)
        selectedCategoriesUrl && fetchItems(selectedCategoriesUrl)
    }
    const setRequestItemSorting = (selectedOption: string)=>{
        const method = selectedOption == SortingOption.Latest+"" ? "latest" : "price"
        const order = selectedOption == SortingOption.PriceDesc+"" ? "desc" : "asc"
        dispatch(ItemsAction.setFetchItemOptions.sorting({method, order}))
    }

    useEffect(()=>{
        dispatch(ItemsAction.setFetchItemOptions.sorting({method: "latest", order: "asc"}))
    }, [])

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