import FormControl from "@mui/material/FormControl"
import MenuItem from "@mui/material/MenuItem"
import Select, { SelectChangeEvent } from "@mui/material/Select"
import { useState } from "react"
import SortingOption from "./SortingOption"
import StyledInputBase from "./StyledInputBase"

const ItemsSortingOption = ()=>{
    const [value, setValue] = useState<string>(SortingOption.Latest+"")
    const onChange = (event: SelectChangeEvent)=>{
        setValue(event.target.value+"")
    }
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