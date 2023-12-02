import { AxiosError, AxiosResponse } from "axios"

import {put, call, takeEvery, select} from "@redux-saga/core/effects"
import { PayloadAction } from "@reduxjs/toolkit"

import CreatePaymentIntentApi from "./CreatePaymentIntentApi"
import CreatePaymentIntentForm from "./models/CreatePaymentIntentForm"
import { CreatePaymentIntentAction } from "./OrderPaymentAction"
import PaymentIntent from "./models/PaymentIntent"
import OrderSelector from "../OrderSelector"
import { RootState } from "../../Store"
import NewOrderItem from "../models/NewOrderItem"

function* createPaymentIntent(form: CreatePaymentIntentForm){
    const {data}: AxiosResponse<PaymentIntent> = yield call(CreatePaymentIntentApi, form)
    yield put(CreatePaymentIntentAction.succeed(data))
}

function handleAxiosError(error: AxiosError){
   return "Error on creating payment intent" 
}

function* tryToCreatePaymentIntent({payload: itemQuantities}: PayloadAction<NewOrderItem[]>){
    const {cachedOrderCreateForm}: RootState["order"] = yield select(OrderSelector)
    const { 
        email: recipientEmail,
        phone: recipientPhone, 
        firstName: recipientFirstName, 
        lastName: recipientLastName,
        ...shippingAddress
    } = cachedOrderCreateForm
    const form: CreatePaymentIntentForm = {
            itemQuantities,
            recipientEmail,
            recipientFirstName,
            recipientLastName,
            recipientPhone,
            ...shippingAddress 
        }

    const {start, end, fail} = CreatePaymentIntentAction
    yield put(start())
    try{
        yield call(createPaymentIntent, form)
    } catch(ex: any){
        const errorMessage = handleAxiosError(ex)
        yield put(fail(errorMessage))
    } finally {
        yield put(end())
    }
}

function* createPaymentIntentSaga(){
    yield takeEvery(CreatePaymentIntentAction.async, tryToCreatePaymentIntent)
}
export default createPaymentIntentSaga