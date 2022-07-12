import { useEffect, useState } from "react"
import ApiRouteSelector from "../../redux/api-routes/ApiRoutesSelector"
import CartItemsAction from "../../redux/cart-items/CartItemsAction"
import { useAppSelector, useAppDispatch } from "../../redux/Hooks"
import useCartCookie from "./useCartCookie"

const useFetchCartItemsByIds = ()=>{
    const {itemIdList} = useCartCookie()
    const apiRoute = useAppSelector(ApiRouteSelector).get.data?.items
    const dispatch = useAppDispatch()
    const fetchItemsById = (itemIds: number[])=>{
        dispatch(CartItemsAction.getItemsById.async(itemIds))
    }

    useEffect(()=>{
        console.log("================= api route or cartCookie is changed ===================")
        if(apiRoute){
            fetchItemsById(itemIdList)
        }
    },[apiRoute, itemIdList])
}
export default useFetchCartItemsByIds