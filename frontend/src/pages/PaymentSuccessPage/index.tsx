import { Box, Button, Link } from "@mui/material";
import { useEffect, useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import useCart from "../../data-sources/cart/useCart";
import stripe from "../PaymentPage/StripePromise";

const PaymentSuccessPage = ()=>{
    const navigate = useNavigate()
    const cart = useCart()
    const [message, setMessage] = useState("")

    const handleContinueShoppingBtnClick = ()=>{
       navigate("/") 
    }

    useEffect(() => {
        const clientSecret = new URLSearchParams(window.location.search).get(
            "payment_intent_client_secret"
        );

        if (!clientSecret) {
            return;
        }

        stripe
            .then(stripe=>stripe?.retrievePaymentIntent(clientSecret))
            .then((result)=>{
                switch (result?.paymentIntent?.status) {
                case "succeeded":
                    setMessage("Payment succeeded! Your order has been placed.");
                    cart.removeAllItems()
                    break;
                case "processing":
                    setMessage("Your payment is processing.");
                    break;
                case "requires_payment_method":
                    setMessage("Your payment was not successful, please try again.");
                    break;
                default:
                    setMessage("Something went wrong.");
                    break;
                }
            })
            .catch(error=>{setMessage(error.message)});
    }, [stripe]);
    return ( 
        <div style={{height: "100%", display: "flex", flexDirection: "column", justifyContent: "center", alignItems: "center"}}>
            <Box sx={{paddingY: "20px"}}>{message}</Box>
            <Button onClick={handleContinueShoppingBtnClick} variant="outlined">Continue shopping</Button>
        </div>
    )
}
export default PaymentSuccessPage