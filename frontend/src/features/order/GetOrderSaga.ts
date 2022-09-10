import { put, call, takeEvery, select } from "@redux-saga/core/effects"
import { PayloadAction } from "@reduxjs/toolkit"
import { AxiosError, AxiosResponse } from "axios"
import HttpStatusCode from "../models/HttpStatusCode"
import GetOrderApi from "./GetOrderApi"
import { GetOrderAction } from "./OrderAction"

function* getOrder(orderId: number){
    const response: AxiosResponse = yield call(GetOrderApi, orderId)
    yield put(GetOrderAction.succeed(response.data))
}

function handleError(error: AxiosError){
    const status = error.response?.status
    if(status == HttpStatusCode.Forbidden){
        return "You do not have permission to access."
    }
    return "Error on fetching order."
}

function* tryToGetOrder({payload: orderId}: PayloadAction<number>){
    const {start, end, fail} = GetOrderAction
    yield put(start())
    try{
        yield call(getOrder, orderId)
    }catch(error: any){
        const errorMessage = handleError(error)
        yield put(fail(errorMessage))
    }finally{
        yield put(end())
    }
}

function* getOrderSaga(){
    yield takeEvery(GetOrderAction.async, tryToGetOrder)
}
export default getOrderSaga