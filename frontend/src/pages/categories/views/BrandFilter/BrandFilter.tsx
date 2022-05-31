import { Box, Card, Checkbox, FormControlLabel, FormGroup, Stack } from "@mui/material"
import React, { ChangeEvent } from "react"
import useViewModel from "./useViewModel"

const BrandFilter = ()=>{
    const viewModel = useViewModel()
    return (<Card sx={{padding: "20px"}}>
        <Box sx={{fontWeight: "bold", fontSize: "18px"}}>Brand filter</Box>
        <FormGroup>
            {viewModel.map((checkBoxViewModel, index)=>
                <FormControlLabel 
                    control={
                        <Checkbox 
                            checked={checkBoxViewModel.state}
                            onChange={(event: ChangeEvent<HTMLInputElement>)=>(checkBoxViewModel.setState(event.target.checked, index))}
                        />
                    }
                    label={checkBoxViewModel.label} 
                />
            )}
        </FormGroup>
    </Card>)
}

export default BrandFilter