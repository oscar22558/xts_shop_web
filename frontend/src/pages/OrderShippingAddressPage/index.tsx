import { Box, Button, Grid, TextField } from "@mui/material"
import StyledTextField from "./SytledTextField"

const ShippingAddressFormPage = ()=>{
    const handleConfirmBtnClick = ()=>{

    }

    return <Box sx={{display: "flex", justifyContent: "center"}}>
        <Grid container sx={{width: "70%"}}>
            <Grid item sx={{fontSize: "20px", marginY: "20px"}}>
                <div>Order shipping address</div>
            </Grid>
            <Grid container direction="column">
                <StyledTextField title="Street address row1" label="Street address row1"/>
                <StyledTextField title="Street address row2" label="Street address row2"/>
                <Box display="flex">
                    <StyledTextField sx={{flex:1, marginRight: "10px" }} label="District"/>
                    <StyledTextField sx={{flex:1 }} label="Area"/>
                </Box>
            </Grid>
            <Grid container sx={{paddingY: "10px"}}>
                <Button sx={{flex: 1}} variant="contained" onClick={handleConfirmBtnClick}>Confirm</Button>
            </Grid>
        </Grid>
    </Box>
}
export default ShippingAddressFormPage