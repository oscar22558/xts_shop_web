import { PayloadAction } from "@reduxjs/toolkit"
import {select, call, put, takeEvery} from "@redux-saga/core/effects";
import AuthenticationAction from "./AuthenticationAction"
import ApiRouteSelector from "../api-routes/ApiRoutesSelector";
import { RootState } from "../Store";
import AuthenticationApi from "./AuthenticationApi";
import { AxiosError, AxiosResponse } from "axios";
import Authentication from "./models/AuthenticationResponse";
import AuthenticationRequest from "./models/AuthenticationRequest";
import ErrorCode from "../ErrorCode";

function handleErrors(ex: AxiosError){
    if(ex.response){
        const errorResponse = ex.response
        if(errorResponse.status === 403){
            return "Username or password invalid"
        }else if(errorResponse.status === 500){
            return ErrorCode[500]
        }else{
            return ErrorCode.default
        }
    }else{
        return ErrorCode.default
    }
}

type ApiRoutesType = RootState["routes"]

function* getAllApiRoutes(): Generator<any, ApiRoutesType, ApiRoutesType>{
    return yield select(ApiRouteSelector)
}

function* postAuthentication(request: AuthenticationRequest): Generator<any, any, any>{
    const apiRoutes = yield call(getAllApiRoutes)
    const authenticationRoute = apiRoutes.get.data?.authentication
    if(authenticationRoute){
        const response = (yield call(AuthenticationApi, {
            url: authenticationRoute,
            data: request
        })) as AxiosResponse
        const data = response.data as Authentication
        yield put(AuthenticationAction.succeed(data))
    }else{
        throw new Error("Authentication Route undefined!")
    }
}

function* tryToPostAuthentication(action: PayloadAction<AuthenticationRequest>){
    const {start, end, fail} = AuthenticationAction
    yield put(start())
    try{
        yield call(postAuthentication, action.payload)
    }catch(ex: any){
        const errorMsg = handleErrors(ex)
        yield put(fail(errorMsg))
    }finally{
        yield put(end())
    }
}

export function* postAuthenticationSaga(){
    yield takeEvery(AuthenticationAction.async, tryToPostAuthentication)
}