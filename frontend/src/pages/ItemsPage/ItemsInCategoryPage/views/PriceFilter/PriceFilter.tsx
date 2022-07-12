import { Button, Card, Stack, TextField } from "@mui/material";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import Slider from "@mui/material/Slider"
import { ChangeEvent, FocusEvent, useState } from "react"
import StyledTextField from "./views/SytledTextField";
import useViewModel from "./useViewModel"
const PriceFilter = ()=>{
    const [value, setValue] = useState([0, 10000])
    const [minSearchPrice, setMinSearchPrice] = useState(0)
    const [maxSearchPrice, setMaxSearchPrice] = useState(10000)
    const viewModel = useViewModel()
    const handleChange = (event: Event, newValue: number | number[])=>{
        setValue(newValue as number[])
        if(typeof newValue != "number"){
            setMinSearchPrice(newValue[0])
            setMaxSearchPrice(newValue[1])
        }
    }
    const handleMinTextFiledChange = (event: ChangeEvent<HTMLInputElement>)=>{
        const fieldValue =  parseInt(event.currentTarget.value)
        const isInputValid = fieldValue >= 0 && fieldValue <= 10000
        if(!isInputValid) return
        setMinSearchPrice(fieldValue)
    }
    const handleMaxTextFiledChange = (event: ChangeEvent<HTMLInputElement>)=>{
        const fieldValue = parseInt(event.currentTarget.value)
        const isInputValid = fieldValue >= 0 && fieldValue <= 10000
        if(!isInputValid) return
        setMaxSearchPrice(fieldValue)
    }
    const handleTextFieldBlur = ()=>{
        const isInputsOrderValid = minSearchPrice <= maxSearchPrice 
        if(!isInputsOrderValid){
            setValue([maxSearchPrice, minSearchPrice])
            setMinSearchPrice(maxSearchPrice)
            setMaxSearchPrice(minSearchPrice)
        }

    }
    return (<Card sx={{padding: "20px"}}>
        <Box sx={{fontWeight: "bold", fontSize: "18px"}}>Price filter</Box>
        <Slider getAriaLabel={()=>"Price"} value={value} onChange={handleChange} min={0} max={10000} disableSwap></Slider>
        <Grid container>
            <Grid item xs={3} sx={{display: "flex", alignItems: "center"}}>HKD $ </Grid>
            <Grid item xs={9}>
                <Stack direction="row" spacing={1}>
                    <StyledTextField size="small" value={minSearchPrice+""} onChange={handleMinTextFiledChange} onBlur={handleTextFieldBlur}/>
                    <Box> - </Box>
                    <StyledTextField size="small" value={maxSearchPrice+""} onChange={handleMaxTextFiledChange} onBlur={handleTextFieldBlur}/>
                </Stack>
            </Grid>
        </Grid>
        <Box sx={{display: "flex", justifyContent: "flex-end"}}>
        <Button onClick={viewModel.handleSearchBtnClick({maxPrice: maxSearchPrice, minPrice: minSearchPrice})}>Search</Button>
        </Box>
    </Card>)
}
export default PriceFilter