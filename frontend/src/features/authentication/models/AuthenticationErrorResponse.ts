import AuthenticationErrorCode from "./AuthenticationErrorCode"

type AuthenticationErrorResponse = {
    username: AuthenticationErrorCode
    password: AuthenticationErrorCode
}
export default AuthenticationErrorResponse