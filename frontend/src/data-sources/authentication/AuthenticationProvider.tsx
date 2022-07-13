import AuthenticationSelector from "../../redux/authentication/AuthenticationSelector"
import { useAppSelector } from "../../redux/Hooks"
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