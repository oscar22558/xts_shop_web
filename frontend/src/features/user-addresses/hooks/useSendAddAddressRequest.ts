import { useAppDispatch } from "../../Hooks"
import AddAddressForm from "../models/AddAddressForm"
import UserAddressAction from "../UserAddressesAction"

const useSentAddAddressRequest = ()=>{
    const dispatch = useAppDispatch()
    return (addAddressForm: AddAddressForm)=>{
        dispatch(UserAddressAction.addAddress.async(addAddressForm))
    }
}
export default useSentAddAddressRequest