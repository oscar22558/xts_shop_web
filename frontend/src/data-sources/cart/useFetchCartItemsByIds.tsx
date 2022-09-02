import { useEffect } from "react"
import ApiRouteSelector from "../../features/api-routes/ApiRoutesSelector"
import CartItemsAction from "../../features/cart-items/CartItemsAction"
import { useAppSelector, useAppDispatch } from "../../features/Hooks"
import useCart from "./useCart"

const useFetchCartItemsByIds = ()=>{
    const {itemCountsInCart} = useCart()
    const apiRoute = useAppSelector(ApiRouteSelector).get.data?.items
    const dispatch = useAppDispatch()

    useEffect(()=>{
        const fetchItemsById = (itemIds: number[])=>{
            if(itemIds.length <=0 ){
                dispatch(CartItemsAction.getItemsById.clear())
                return
            }
            dispatch(CartItemsAction.getItemsById.async(itemIds))
        }
        if(apiRoute){
            const ids = Object.keys(itemCountsInCart)
                .map((itemId)=>Number.parseInt(itemId))
            fetchItemsById(ids)
        }
    },[apiRoute, itemCountsInCart, dispatch])
}
export default useFetchCartItemsByIds