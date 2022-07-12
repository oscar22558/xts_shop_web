import { Action } from "@reduxjs/toolkit"
import {select, call, put, takeEvery} from "@redux-saga/core/effects";
import AuthenticationAction from "./AuthenticationAction"
import ApiRouteSelector from "../api-routes/ApiRoutesSelector";
import { RootState } from "../Store";
import AuthenticationApi from "./AuthenticationApi";
import { AxiosResponse } from "axios";
import Authentication from "./models/Authentication";

function* postAuthentication(){
    const apiRoutes = (yield select(ApiRouteSelector)) as RootState["routes"]
    const authenticationRoute = apiRoutes.get.data?.authentication
    if(authenticationRoute){
        const response = (yield call(AuthenticationApi, authenticationRoute)) as AxiosResponse
        const data = response.data as Authentication
        yield AuthenticationAction.succeed(data)
    }else{
        throw new Error("Authentication Route undefined!")
    }
}

function* tryToPostAuthentication(action: Action){
    const {start, end, fail} = AuthenticationAction
    yield put(start())
    try{
        yield call(postAuthentication)
    }catch(ex: any){
        yield put(fail(ex?.message))
    }finally{
        yield put(end())
    }
}

export function* postAuthenticationSaga(){
    yield takeEvery(AuthenticationAction.async, tryToPostAuthentication)
}
