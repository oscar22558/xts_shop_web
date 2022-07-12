import useCart from "../../../data-sources/cart/useCart"
import { useNavigate } from "react-router-dom"
const ShoppingCartIcon = ()=>{
    const {itemTotalCountInCart: itemCountInCart} = useCart()
    const navigate = useNavigate()
    return <>
        <div onClick={()=>{
           navigate('cart') 
        }}>Shopping Cart ({itemCountInCart()})</div>
    </>
}
export default ShoppingCartIcon