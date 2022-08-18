import { useEffect } from "react"
import ApiRouteSelector from "../../redux/api-routes/ApiRoutesSelector"
import CartItemsAction from "../../redux/cart-items/CartItemsAction"
import { useAppSelector, useAppDispatch } from "../../redux/Hooks"
import useCart from "./useCart"

const useFetchCartItemsByIds = ()=>{
    const {itemCountsInCart} = useCart()
    const apiRoute = useAppSelector(ApiRouteSelector).get.data?.items
    const dispatch = useAppDispatch()

    useEffect(()=>{
        const fetchItemsById = (itemIds: number[])=>{
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