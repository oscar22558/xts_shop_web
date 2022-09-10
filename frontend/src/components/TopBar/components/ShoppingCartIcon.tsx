import useCart from "../../../data-sources/cart/useCart"
import AppRouteList from "../../../routes/AppRouteList"
import { Link } from "@mui/material"

const ShoppingCartIcon = ()=>{
    const {itemTotalCountInCart: itemCountInCart} = useCart()
    return <>
        <Link href={AppRouteList.cart} color="#fff" underline="none">Shopping Cart ({itemCountInCart()})</Link>
    </>
}
export default ShoppingCartIcon