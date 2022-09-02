import { useContext } from "react"
import AuthenticationSelector from "../../features/authentication/AuthenticationSelector"
import { useAppSelector } from "../../features/Hooks"
import AuthenticationContext from "./AuthenticationContext"

const useAuthentication = ()=>{
    const context = useContext(AuthenticationContext)
    const isUserAuthenticated = context?.token != null
    const {error} = useAppSelector(AuthenticationSelector).authentication
    return {
        ...context,
        isUserAuthenticated,
        error
    }
}
export default useAuthentication