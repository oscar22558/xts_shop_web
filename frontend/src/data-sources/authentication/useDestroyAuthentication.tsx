import { DestroyAuthenticationAction } from "../../redux/authentication/AuthenticationAction"
import { useAppDispatch } from "../../redux/Hooks"

const useDestroyAuthentication = ()=>{
    const dispatch = useAppDispatch()
    return ()=>{
        dispatch(DestroyAuthenticationAction())
    }
}
export default useDestroyAuthentication