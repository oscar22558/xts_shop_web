import { createAction } from "@reduxjs/toolkit"
import { AuthenticationSlice } from "./AuthenticationReducer"
import AuthenticationRequest from "./models/AuthenticationRequest"

const actions = AuthenticationSlice.actions
export const AuthenticationAction = {
    start: actions.authenticationStart,
    end: actions.authenticationEnd,
    succeed: actions.authenticationSucceed,
    fail: actions.authenticationFail,
    async: createAction<AuthenticationRequest>("authentication/authentication/async"),
    clearError: actions.clearAuthenticationError,
}

export const ValidAuthTokenAction = {
    start: actions.validAuthTokenStart,
    end: actions.validAuthTokenEnd,
    succeed: actions.validAuthTokenSucceed,
    fail: actions.validAuthTokenFail,
    async: createAction("authentication/valid-auth-token/async")
}

export const DestroyAuthenticationAction = actions.destroyAuthentication
export default AuthenticationAction