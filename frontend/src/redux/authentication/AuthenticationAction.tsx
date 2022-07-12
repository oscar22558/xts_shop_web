import { createAction } from "@reduxjs/toolkit"
import { AuthenticationSlice } from "./AuthenticationReducer"

const actions = AuthenticationSlice.actions
const AuthenticationAction = {
    start: actions.authenticationStart,
    end: actions.authenticationEnd,
    succeed: actions.authenticationSucceed,
    fail: actions.authenticationFail,
    async: createAction("authentication/async")
}

export default AuthenticationAction