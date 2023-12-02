import { useAppSelector } from "../../features/Hooks";
import CartItemsSelector from "../../features/cart-items/CartItemsSelector"
import useCartCookie from "./useCartCookie";
import CartCookieType from "./models/CartCookieType";

const useCart = ()=>{
    const {itemsInCart: itemsInCartApiResponse} = useAppSelector(CartItemsSelector)
    const {cartCookie, setCartCookie} = useCartCookie()
    const addCartItem = (itemId: number, quantity: number = 1)=>{
        if(cartCookie[itemId] === undefined){
            cartCookie[itemId] = 0
        }
        cartCookie[itemId] += quantity
        setCartCookie(cartCookie)
    }

    const minusItemQunity = (itemId: number, quantity: number = 1)=>{
        const currentItemQuantity = cartCookie[itemId]
        if(currentItemQuantity != null && currentItemQuantity > quantity){
            cartCookie[itemId] -= quantity
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

    const removeAllItems = ()=>{
        setCartCookie({})
    }

    return {
        itemCountsInCart: cartCookie,
        itemsInCart: itemsInCartApiResponse.data,
        addCartItem,
        minusItemQunity,
        itemTotalCountInCart,
        removeItems,
        removeAllItems
    }
}
export default useCart