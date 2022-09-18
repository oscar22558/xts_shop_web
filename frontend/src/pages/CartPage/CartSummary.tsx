import { Box, Grid } from "@mui/material"

import CartItemList from "./CartItemList"
import OrderSummary from "./OrderSummary"

import useFetchCartItemsByIds from "../../data-sources/cart/useFetchCartItemsByIds"

const CartSummary = ()=>{
    useFetchCartItemsByIds() 
    return (
        <Grid container direction="row">
            <Grid item xs={9}>
                <CartItemList />
            </Grid>
            <Grid item xs={3}>
                <OrderSummary />
            </Grid>
        </Grid>
    )
}
export default CartSummary