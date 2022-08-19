import { useEffect } from "react"
import GetInvoiceAction from "../../redux/cart-items/invoice/InvoiceAction"
import { useAppDispatch } from "../../redux/Hooks"
import useCart from "./useCart"

const useFetchInvoice = ()=>{
    const { itemCountsInCart } = useCart()
    const dispatch = useAppDispatch()
    useEffect(()=>{
        const getInvoiceRequest = {
            itemQuantities: Object.keys(itemCountsInCart)
                .map(itemId=>Number.parseInt(itemId))
                .map(itemId=>({itemId, quantity: itemCountsInCart[itemId]}))
        }
        dispatch(GetInvoiceAction.async(getInvoiceRequest))
    }, [itemCountsInCart, dispatch])
}
export default useFetchInvoice