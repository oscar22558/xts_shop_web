import { Box, Typography, Grid, Button } from "@mui/material"
import { useNavigate } from "react-router-dom"
import CreateAddressSection from "../SettingsPage/AddressPage/CreateAddressSection"
import AddressSelectionForm from "./AddressSelectionForm"

const ShippingAddressFormPage = ()=>{
    const navigate = useNavigate()
    const handleNextBtnClick = ()=>{
        navigate("/payment")
    }

    return <Box sx={{paddingY: "20px"}}>
        <Typography variant="h4">Order shipping address</Typography>
        <Grid container spacing="20px">
            <Grid item xs={9}>
                <AddressSelectionForm /> 
                <CreateAddressSection />
            </Grid>
            <Grid item xs={3}>
                <Box sx={{borderRadius: "15px", border: "1px solid", height: "300px"}}>
                    <Button onClick={handleNextBtnClick}>Next</Button>
                </Box>
            </Grid>
        </Grid>
    </Box>

}
export default ShippingAddressFormPage