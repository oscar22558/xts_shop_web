import { useEffect } from "react"
import ApiRouteSelector from "../../features/api-routes/ApiRoutesSelector"
import CartItemsAction from "../../features/cart-items/CartItemsAction"
import { useAppSelector, useAppDispatch } from "../../features/Hooks"
import useCart from "./useCart"

const useFetchCartItemsByIds = ()=>{
    const {itemCountsInCart} = useCart()
    const apiRoute = useAppSelector(ApiRouteSelector).get.data?.items
    const dispatch = useAppDispatch()
    console.log("outside useEffect Route: "+apiRoute)
    useEffect(()=>{
        console.log("initial fetch cart items process...")
        const fetchItemsById = (itemIds: number[])=>{
            console.log("fetching cart items...")
            if(itemIds.length <=0 ){
                console.log("clear cart items...")
                dispatch(CartItemsAction.getItemsById.clear())
                return
            }

            console.log("start fetching items...")
            dispatch(CartItemsAction.getItemsById.async(itemIds))
        }
        console.log("confirm route defined...")
        console.log("Route: "+apiRoute)
        if(apiRoute){
            console.log("route defined...")
            const ids = Object.keys(itemCountsInCart)
                .map((itemId)=>Number.parseInt(itemId))
            fetchItemsById(ids)
        }
    },[apiRoute, itemCountsInCart, dispatch])
}
export default useFetchCartItemsByIds