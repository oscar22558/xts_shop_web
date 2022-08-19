import { Box, Button, Divider, Grid } from "@mui/material"
import { useNavigate } from "react-router-dom"
import useAuthentication from "../../../data-sources/authentication/useAuthentication"
import useFetchInvoice from "../../../data-sources/cart/useFetchInvoice"
import InvoiceSelector from "../../../redux/cart-items/invoice/InvoiceSelector"
import { useAppSelector } from "../../../redux/Hooks"
import AppRouteList from "../../../routes/AppRouteList"

const OrderSummary = ()=>{
    useFetchInvoice()
    const { data: invoice } = useAppSelector(InvoiceSelector).getInvoiceResponse
    const {subItemTotal, shippingFree, total} = invoice
    

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
                    <div>{subItemTotal}</div>
                    <div>{shippingFree}</div>
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