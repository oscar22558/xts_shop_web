import { useAppDispatch } from "../../features/Hooks"
import AuthenticationAction from "../../features/authentication/AuthenticationAction"
import AuthenticationRequest from "../../features/authentication/models/AuthenticationRequest"

const useAuthenticateUser = ()=>{
    const dispatch = useAppDispatch()
    return (request: AuthenticationRequest)=>{
        dispatch(AuthenticationAction.async(request))
    }
    
}
export default useAuthenticateUser