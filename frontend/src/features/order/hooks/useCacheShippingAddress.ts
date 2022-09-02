import { useAppDispatch, useAppSelector } from "../../Hooks"
import { CachedOrderAction } from "../OrderAction"
import OrderSelector from "../OrderSelector"

const useCacheShippingAddress = ()=>{
    const dispatch = useAppDispatch()
    const cachedOrder = useAppSelector(OrderSelector).cachedOrderCreateForm
    return (userAddressId: number)=>{
        dispatch(CachedOrderAction.update({...cachedOrder, userAddressId}))
    }
}
export default useCacheShippingAddress