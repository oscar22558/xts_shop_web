import { useAppSelector } from "../../redux/Hooks";
import CartItemsSelector from "../../redux/cart-items/CartItemsSelector"
import useCartCookie from "./useCartCookie";

const useCart = ()=>{
    const {itemsInCart: itemsInCartApiResponse} = useAppSelector(CartItemsSelector)
    const {cartCookie, setCartCookie} = useCartCookie()
    const addCartItem = (itemId: number)=>{
        if(cartCookie[itemId] === undefined){
            cartCookie[itemId] = 0
        }
        cartCookie[itemId] += 1
        setCartCookie(cartCookie)
    }

    const removeItem = (itemId: number)=>{
        if(cartCookie[itemId] != null && cartCookie[itemId] > 1){
            cartCookie[itemId] -= 1
        }
        setCartCookie(cartCookie)
    }

    const itemTotalCountInCart = ()=>{
        const itemCounts = Object.values(cartCookie)
        return itemCounts.reduce((total, itemCount)=> total+itemCount, 0)
    }

    return {
        itemCountsInCart: cartCookie,
        itemsInCart: itemsInCartApiResponse.data,
        addCartItem,
        removeItem,
        itemTotalCountInCart
    }
}
export default useCart