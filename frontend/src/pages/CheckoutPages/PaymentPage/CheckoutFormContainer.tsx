import { Container, Typography, Box } from "@mui/material"

import CheckoutForm from "./CheckoutForm"
import { useAppSelector } from "../../../features/Hooks"
import OrderPaymentSelector from "../../../features/order/payment/OrderPaymentSelector"


const CheckoutFormContainer = ()=>{

    const {orderTotal: paymentTotal} = useAppSelector(OrderPaymentSelector).createPaymentIntentResponse.data

    return (
        <Container sx={{paddingY: "20px"}} maxWidth="sm">
            <Typography variant="h5" sx={{marginY: "10px"}}>Settle Payment</Typography>
            <Box sx={{display: "flex", flexDirection: "column", justifyContent: "center", alignItems: "center"}}>
                <Box sx={{width: "100%"}}>
                    <CheckoutForm paymentTotal={paymentTotal}/>
                </Box>
            </Box>
        </Container>
    )
}
export default CheckoutFormContainer