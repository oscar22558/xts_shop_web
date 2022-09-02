import { useAppDispatch } from "../../Hooks"
import UserAddressAction from "../UserAddressesAction"

const useSendDeleteAddressRequest = ()=>{
    const dispatch = useAppDispatch()
    return (addressId: number)=>{
        dispatch(UserAddressAction.deleteAddress.async({addressId}))
    }
}
export default useSendDeleteAddressRequest