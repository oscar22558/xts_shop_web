import { Box, Button, Divider, Grid } from "@mui/material"
import { useNavigate } from "react-router-dom"
import useAuthentication from "../../../data-sources/authentication/useAuthentication"
import AppRouteList from "../../../routes/AppRouteList"

const OrderSummary = ()=>{
    const subtotal = 1
    const shipping = 1
    const total = 2

    const navigate = useNavigate()
    const authentication = useAuthentication()

    const navigateToSignInPage = ()=>{
        navigate(AppRouteList.signIn)
    }
    
    const navigateToShippingAddressFormPage = ()=>{
        navigate(AppRouteList.orderShippingAddresses)
    }

    const handleCheckOutBtnClick = ()=>{
       if(authentication.isUserAuthenticated){
            navigateToShippingAddressFormPage()
       }else{
            navigateToSignInPage()
       }
    }

    return <Box sx={{border: "1px solid", borderRadius: "10px", padding: "20px", display: "flex", flexDirection: "column",justifyContent: "stretch"}}>
        <div>Order summary</div>
        <br/>
        <Grid container>
            <Grid item xs={9}>
                <div>Subtotal:</div>
                <div>Shipping:</div>
                <div>Total:</div>
            </Grid>
            <Grid item xs={3} >
                <Box sx={{diplay: "flex", alignItems: "flex-end", flexDirection: "column", textAlign: "right"}}>
                    <div>{subtotal}</div>
                    <div>{shipping}</div>
                    <div>{total}</div>
                </Box>
            </Grid>
        </Grid>
        <Divider />
        <Button 
            variant="contained" 
            sx={{marginTop: "10px"}}
            onClick={handleCheckOutBtnClick}
        >
            Check out
        </Button>
    </Box>
}
export default OrderSummary