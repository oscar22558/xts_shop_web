import { useEffect } from "react";

import { Appearance } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";

import { useAppDispatch, useAppSelector } from "../../../features/Hooks";
import OrderSelector from "../../../features/order/OrderSelector";

import useCart from "../../../data-sources/cart/useCart";
import { CreatePaymentIntentAction } from "../../../features/order/payment/OrderPaymentAction";
import OrderPaymentSelector from "../../../features/order/payment/OrderPaymentSelector";
import stripePromise from "./StripePromise";
import CheckoutFormContainer from "./CheckoutFormContainer";


const PaymentPage = ()=>{
    const dispatch = useAppDispatch()
    const {clientSecret} = useAppSelector(OrderPaymentSelector).createPaymentIntentResponse.data

    const cachedOrderCreateForm = useAppSelector(OrderSelector).cachedOrderCreateForm
    const { itemCountsInCart } = useCart()

    useEffect(() => {
        const itemQuantities = Object.entries(itemCountsInCart).map(carEntity=>({ 
            itemId: Number.parseInt(carEntity[0]), 
            quantity: carEntity[1]
        }))
        dispatch(CreatePaymentIntentAction.async(itemQuantities))
    }, [itemCountsInCart, cachedOrderCreateForm, dispatch]);

    const appearance: Appearance = {
        theme: 'stripe',
    };
    const options = {
        clientSecret,
        appearance,
    };

  return (
    clientSecret ? (
        <Elements options={options} stripe={stripePromise}>
            <CheckoutFormContainer />
        </Elements>
    ) : (
        <div style={{height: "100%", display: "flex", justifyContent: "center", alignItems: "center"}}>
            <span>Setting up payment environment...</span>
        </div>
    )
  );
}
export default PaymentPage