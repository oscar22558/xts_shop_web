import { createContext } from "react"
import AuthenticationResponse from "../../redux/authentication/models/AuthenticationResponse"

type ContextType = AuthenticationResponse | undefined

const AuthenticationContext = createContext<ContextType>(undefined)

export default AuthenticationContext