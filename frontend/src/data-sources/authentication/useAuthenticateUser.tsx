import { useAppDispatch } from "../../redux/Hooks"
import AuthenticationAction from "../../redux/authentication/AuthenticationAction"
import AuthenticationRequest from "../../redux/authentication/models/AuthenticationRequest"

const useAuthenticateUser = ()=>{
    const dispatch = useAppDispatch()
    return (request: AuthenticationRequest)=>{
        dispatch(AuthenticationAction.async(request))
    }
    
}
export default useAuthenticateUser