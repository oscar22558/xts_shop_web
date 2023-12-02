import { createContext } from "react"
import AuthenticationResponse from "../../features/authentication/models/AuthenticationResponse"

type ContextType = AuthenticationResponse | undefined

const AuthenticationContext = createContext<ContextType>(undefined)

export default AuthenticationContext