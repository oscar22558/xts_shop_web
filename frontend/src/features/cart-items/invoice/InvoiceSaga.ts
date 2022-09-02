import { takeEvery, put, call, select } from "@redux-saga/core/effects"
import { PayloadAction } from "@reduxjs/toolkit";
import { AxiosError, AxiosResponse } from "axios";
import GetInvoiceAction from "./InvoiceAction";
import GetInvoiceRequest from "./models/GetInvoiceRequest";
import GetInvoiceApi from "./InvoiceApi"

function* getInvoice(request: GetInvoiceRequest){
    const response: AxiosResponse = yield call(GetInvoiceApi, request)
    yield put(GetInvoiceAction.succeed(response.data))
}

function hanldeError(ex: AxiosError){
    return "Error in getting order summary."
}

function* tryToGetInvoice({payload}: PayloadAction<GetInvoiceRequest>){
    const { start, end, fail } = GetInvoiceAction
    yield put(start())
    try{
        yield call(getInvoice, payload)
    }catch(ex: any){
        const message = hanldeError(ex)
        yield put(fail(message))
    }finally{
        yield put(end())
    }
}

function* getInvoiceSaga(){
    yield takeEvery(GetInvoiceAction.async, tryToGetInvoice)
}

export default getInvoiceSaga