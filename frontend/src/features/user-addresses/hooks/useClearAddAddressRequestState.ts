import { useAppDispatch } from "../../Hooks"
import UserAddressAction from "../UserAddressesAction"

const useClearAddAddressRequestState = ()=>{
    const dispatch = useAppDispatch()
    return ()=>{
        dispatch(UserAddressAction.addAddress.end)
        dispatch(UserAddressAction.addAddress.clearError)
    }
}
export default useClearAddAddressRequestState