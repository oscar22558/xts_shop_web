import { Box } from "@mui/material"
import useCart from "../../data-sources/cart/useCart"
import CartSummary from "./CartSummary"
import NotItemInCartAlert from "./NoItemInCartAlert"

const CartPage = ()=>{
    const {itemTotalCountInCart} = useCart()
    const isNoItemInCartAlertShown = itemTotalCountInCart() <= 0
    return <Box sx={{paddingY: "25px"}}>
        <h1>Shopping Cart</h1>
        {isNoItemInCartAlertShown ? <NotItemInCartAlert /> : <CartSummary />}
    </Box>
}

export default CartPage