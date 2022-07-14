import useCart from "../../../data-sources/cart/useCart"
import { useNavigate } from "react-router-dom"
import AppRouteList from "../../../routes/AppRouteList"
const ShoppingCartIcon = ()=>{
    const {itemTotalCountInCart: itemCountInCart} = useCart()
    const navigate = useNavigate()
    return <>
        <div onClick={()=>{
           navigate(AppRouteList.cart) 
        }}>Shopping Cart ({itemCountInCart()})</div>
    </>
}
export default ShoppingCartIcon