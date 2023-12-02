import AuthenticationSelector from "../../features/authentication/AuthenticationSelector"
import { useAppSelector } from "../../features/Hooks"
import AuthenticationContext from "./AuthenticationContext"

type Props = {
    children?: JSX.Element | JSX.Element[] | null
}

const AuthenticationProvider = ({children}: Props)=>{
    const {authentication} = useAppSelector(AuthenticationSelector)
    return <AuthenticationContext.Provider value={authentication.data}>
        {children}
    </AuthenticationContext.Provider>
}

export default AuthenticationProvider