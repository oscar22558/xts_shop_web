import { PayloadAction } from "@reduxjs/toolkit"
import {call, put, takeEvery} from "@redux-saga/core/effects";
import { AxiosError, AxiosResponse } from "axios";

import AuthenticationAction from "./AuthenticationAction"
import AuthenticationApi from "./AuthenticationApi";
import Authentication from "./models/AuthenticationResponse";
import AuthenticationRequest from "./models/AuthenticationRequest";

import { setAuthorizationHeader } from "../ApiRequest";
import ErrorCode from "../ErrorCode";
import GetUserAction from "../user/UserAction";
import AuthenticationErrorCode from "./models/AuthenticationErrorCode";
import AuthenticationErrorResponse from "./models/AuthenticationErrorResponse";
import AuthenticationFormError from "./models/AuthenticationFormError";

function* getUser(){
    yield put(GetUserAction.async())
}

function handleUsernameColumnError(usernameError: AuthenticationErrorCode){
    if(usernameError == null){
        return ""
    }
    if(usernameError === AuthenticationErrorCode.USERNAME_EMPTY){
        return "Username cannot be empty."
    }
    return "Username column invalid."
}

function handlePasswordColumnError(passwordError: AuthenticationErrorCode){
    if(passwordError == null){
        return ""
    }
    if(passwordError === AuthenticationErrorCode.PASSWORD_EMPTY){
        return "Password cannot be empty."
    }
    return "Password column invalid."
}

function handleUnprocessableEntityError({username: usernameColumnError, password: passwordColumnError}: AuthenticationErrorResponse){
    const username = handleUsernameColumnError(usernameColumnError)
    const password = handlePasswordColumnError(passwordColumnError)
    return {username, password, form: ""}
}

function handleErrors(ex: AxiosError<AuthenticationErrorResponse>): AuthenticationFormError{
    const formErrorMessage = {username: "", password: "", form: ""}
    if(!ex.response){
        return {...formErrorMessage, form: ErrorCode.default}
    }

    const responseStatus = ex.response.status
    if(responseStatus === 403){
        return {...formErrorMessage, form: "Username or password invalid"}
    }
    if(responseStatus === 500){
        return {...formErrorMessage, form: ErrorCode[500]}
    }
    if(responseStatus === 422){
        return handleUnprocessableEntityError(ex.response.data)
    }
    return {...formErrorMessage, form: ErrorCode.default}
}

function* postAuthentication(request: AuthenticationRequest): Generator<any, any, any>{
    const response: AxiosResponse = yield call(AuthenticationApi, request)
    const data: Authentication = response.data
    setAuthorizationHeader(data.token ?? "")
    yield put(AuthenticationAction.succeed(data))
    yield call(getUser)
}

function* tryToPostAuthentication(action: PayloadAction<AuthenticationRequest>){
    const {start, end, fail} = AuthenticationAction
    yield put(start())
    try{
        yield call(postAuthentication, action.payload)
    }catch(ex: any){
        const errorMessages = handleErrors(ex)
        yield put(fail(errorMessages))
    }finally{
        yield put(end())
    }
}

export function* postAuthenticationSaga(){
    yield takeEvery(AuthenticationAction.async, tryToPostAuthentication)
}