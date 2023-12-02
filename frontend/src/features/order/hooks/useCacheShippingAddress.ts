import { useAppDispatch, useAppSelector } from "../../Hooks"
import UserSelector from "../../user/UserSelector"
import { CachedOrderAction } from "../OrderAction"
import OrderSelector from "../OrderSelector"

const useCacheShippingAddress = ()=>{
    const dispatch = useAppDispatch()
    const {addresses} = useAppSelector(UserSelector).getUserResponse.data
    const cachedOrder = useAppSelector(OrderSelector).cachedOrderCreateForm
    return (userAddressId: number)=>{
        const {id, ...address} = addresses.find(address=>address.id === userAddressId) ?? {
            id: "",
            country: "",
            city: "",
            area: "",
            district: "",
            row1: "",
            row2: ""
        }
        
        dispatch(CachedOrderAction.update({...cachedOrder, ...address}))
    }
}
export default useCacheShippingAddress