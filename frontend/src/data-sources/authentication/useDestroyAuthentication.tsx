import { DestroyAuthenticationAction } from "../../features/authentication/AuthenticationAction"
import { useAppDispatch } from "../../features/Hooks"

const useDestroyAuthentication = ()=>{
    const dispatch = useAppDispatch()
    return ()=>{
        dispatch(DestroyAuthenticationAction())
    }
}
export default useDestroyAuthentication