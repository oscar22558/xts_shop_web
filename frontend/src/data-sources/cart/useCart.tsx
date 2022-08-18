import { useAppSelector } from "../../redux/Hooks";
import CartItemsSelector from "../../redux/cart-items/CartItemsSelector"
import useCartCookie from "./useCartCookie";
import CartCookieType from "./models/CartCookieType";

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

    const minusItemQunity = (itemId: number)=>{
        if(cartCookie[itemId] != null && cartCookie[itemId] > 1){
            cartCookie[itemId] -= 1
        }
        setCartCookie(cartCookie)
    }

    const removeItems = (itemIds: number[])=>{
        const remainingIds = Object.keys(cartCookie).filter(itemIdInCart=>
            itemIds.findIndex(itemId=>itemId.toString()===itemIdInCart) === -1
        )
        const newCartCookit: CartCookieType = {}
        remainingIds.forEach(id=>newCartCookit[id] = cartCookie[id])
        setCartCookie(newCartCookit)
    }

    const itemTotalCountInCart = ()=>{
        const itemCounts = Object.values(cartCookie)
        return itemCounts.reduce((total, itemCount)=> total+itemCount, 0)
    }

    return {
        itemCountsInCart: cartCookie,
        itemsInCart: itemsInCartApiResponse.data,
        addCartItem,
        minusItemQunity,
        itemTotalCountInCart,
        removeItems
    }
}
export default useCart