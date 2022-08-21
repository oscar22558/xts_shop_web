import { SelectChangeEvent, FormControl, InputLabel, MenuItem, FormHelperText } from "@mui/material"
import { useState } from "react"
import StyledSelect from "./StyledSelect"

export type SelectWithFormControlEventType<V> = (value: V)=>void

type Props<D extends object> = {
    id: string
    dataSet: D
    error: boolean
    errorText: string
    onChange?: SelectWithFormControlEventType<keyof D>
}

const SelectWithFormControl = <D extends object>({
    id,
    dataSet: menuItemDataSet,
    error,
    errorText: errorMsg,
    onChange
}: Props<D>)=>{
    type MenuItemIndexType = keyof typeof menuItemDataSet
    const menuItemViewModels = Object.entries(menuItemDataSet)
    const [selectedDistrictIndex, setSelectedDistrictIndex] = useState<MenuItemIndexType|"">("")

    const handleChange = (event: SelectChangeEvent<unknown>)=>{
        const value = event.target.value
        onChange && onChange(value as MenuItemIndexType)
        setSelectedDistrictIndex(event.target.value as MenuItemIndexType)
    }

    return <FormControl fullWidth>
        <InputLabel id={`${id}-select-label`}>{id}</InputLabel>
        <StyledSelect
            labelId={`${id}-select-label`}
            id={`${id}-select`}
            value={selectedDistrictIndex}
            label={id}
            error={error}
            onChange={handleChange}
        >
            {
                menuItemViewModels.map(menuItemViewModel=>(
                    <MenuItem value={menuItemViewModel[0]}>{menuItemViewModel[1]}</MenuItem>
                ))
            }
        </StyledSelect>
        {error && <FormHelperText error>{errorMsg}</FormHelperText>}
    </FormControl>
}
export default SelectWithFormControl