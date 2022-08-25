import { useEffect, useState } from "react";

import { Box, Container, Typography } from "@mui/material";

import { Appearance, loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";

import { useAppSelector } from "../../redux/Hooks";
import AuthenticationSelector from "../../redux/authentication/AuthenticationSelector";
import OrderSelector from "../../redux/order/OrderSelector";

import useCart from "../../data-sources/cart/useCart";
import CheckoutForm from "./CheckoutForm";
import ApiConfig from "../../redux/ApiConfig"

const stripePromise = loadStripe("pk_test_51LS0ODIlTFFvV5CYLojONvwgp65Y0XGQ5FnXcJHdp4dJ0npvi3bmUebYlPqfv2HZwzWueAIoKdxgpqIRDY5ufQg600dws9t0jV");

const PaymentPage = ()=>{
    const [ isWaitingUserPay, setIsWaitingUserPay ] = useState(true)
    const [ isUserFinishedPayment, setIsUserFinishedPayment ] = useState(false)
    const [ clientSecret, setClientSecret ] = useState("")

    const { data } = useAppSelector(AuthenticationSelector).authentication
    const { userAddressId } = useAppSelector(OrderSelector).cachedOrderCreateForm
    const { itemCountsInCart } = useCart()

    const debug = false

    useEffect(() => {
        const requestUrl = `${ApiConfig.baseURL}/payment-intent`
        const headers = { 
            "Content-Type": "application/json",
            "Authorization": `Bearer ${data.token}`
        }
        const itemQuantities = Object.entries(itemCountsInCart).map(carEntity=>({ 
            itemId: Number.parseInt(carEntity[0]), 
            quantity: carEntity[1]
        }))
        const createPaymentIntentForm = {
            itemQuantities,
            userAddressId
        }
        fetch(requestUrl, {
            method: "POST",
            headers,
            body: JSON.stringify(createPaymentIntentForm)
        })
            .then((response) => response.json())
            .then((data: any)=> setClientSecret(data.clientSecret));

    }, [itemCountsInCart, userAddressId, data.token]);

    useEffect(()=>{
        const requestUrl = `${ApiConfig.baseURL}/payment-intent`
        const headers = { 
            "Content-Type": "application/json",
            "Authorization": `Bearer ${data.token}`
        }
        return ()=>{
            if(!clientSecret || isUserFinishedPayment){
                return
            }
            if(clientSecret && !isWaitingUserPay){
               setIsWaitingUserPay(true) 
               return
            }
            stripePromise
                .then(stripe=>stripe?.retrievePaymentIntent(clientSecret))
                .then(paymentIntentResult=>{
                    const paymentIntentId = paymentIntentResult?.paymentIntent?.id
                    if(!paymentIntentId) {
                        throw new Error("Payment intent is null")
                    }
                    return fetch(requestUrl, {
                        method: "DELETE",
                        body: JSON.stringify({paymentIntentId}),
                        headers
                    })
                }).then(()=>{})
                .catch(error=>console.error(error))
        }
    }, [clientSecret, isUserFinishedPayment, isWaitingUserPay, data.token])

    const appearance: Appearance = {
        theme: 'stripe',
    };
    const options = {
        clientSecret,
        appearance,
    };

  return (
    <Container sx={{paddingY: "20px"}} maxWidth="sm">
      <Typography variant="h5">Pay your order</Typography>
      <Box sx={{display: "flex", flexDirection: "column", justifyContent: "center", alignItems: "center"}}>
        {
            debug 
            ? <Box sx={{width: "100%", height: "500px", borderRadius: "10px", border: "1px solid #aeaeae"}}></Box>
            : (<Box sx={{width: "100%"}}>
                {clientSecret && (
                    <Elements options={options} stripe={stripePromise}>
                    <CheckoutForm onUserFinishedPayment={setIsUserFinishedPayment}/>
                    </Elements>
                )}
            </Box>)
        }
      </Box>
    </Container>
  );
}
export default PaymentPage