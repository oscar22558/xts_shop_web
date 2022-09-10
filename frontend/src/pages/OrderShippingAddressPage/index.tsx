import { Box, Typography, Grid, Button } from "@mui/material"
import { useNavigate } from "react-router-dom"
import { useAppSelector } from "../../features/Hooks"
import OrderSelector from "../../features/order/OrderSelector"
import UserSelector from "../../features/user/UserSelector"
import CreateAddressSection from "../SettingsPage/AddressPage/CreateAddressSection"
import AddressSelectionForm from "./AddressSelectionForm"

const ShippingAddressFormPage = ()=>{
    const navigate = useNavigate()
    const cachedOrder = useAppSelector(OrderSelector).cachedOrderCreateForm
    const {addresses} = useAppSelector(UserSelector).getUserResponse.data
    const address = addresses.find(address=>address.id === cachedOrder.userAddressId)
    const handleNextBtnClick = ()=>{
        navigate("/payment")
    }

    return <Box sx={{paddingY: "25px"}}>
        <h1>Select Shipping address</h1>
        <Grid container spacing="20px">
            <Grid item xs={9}>
                <AddressSelectionForm /> 
                <CreateAddressSection />
            </Grid>
            <Grid item xs={3} >
                <Box sx={{borderRadius: "15px", border: "1px solid", display: "flex", flexDirection: "column" , padding: "20px"}}>
                    <Box sx={{flex: 1, display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center"}}>
                        {address ? <>
                            <div>{address.row1}</div>
                            <div>{address.row2 ? address.row2 : "-"}</div>
                            <div>{address.area}</div>
                            <div>{address.district}</div>
                        </>: undefined}
                    </Box>
                    <Box sx={{display: "flex", justifyContent: "center", marginTop: "20px"}}>
                        <Button disabled={address == null} variant="contained" onClick={handleNextBtnClick}>Next</Button>
                    </Box>
                </Box>
            </Grid>
        </Grid>
    </Box>

}
export default ShippingAddressFormPage