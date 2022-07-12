import { Box, Grid } from "@mui/material"
import { useEffect } from "react"
import CartItemList from "./CartItemList"
import OrderSummary from "./OrderSummary"

const CartPage = ()=>{
    return <Box sx={{marginTop: "25px"}}>
        <h1>Shopping Cart</h1>
        <Grid container direction="row">
            <Grid item xs={9}>
                <CartItemList />
            </Grid>
            <Grid item xs={3}>
                <OrderSummary />
            </Grid>
        </Grid>
    </Box>
}

export default CartPage