import {put, call, takeEvery} from "@redux-saga/core/effects"
import { PayloadAction } from "@reduxjs/toolkit"
import { AxiosError } from "axios"
import HttpStatusCode from "../models/HttpStatusCode"
import RegistryAction from "./RegistryAction"
import RegistryApi from "./RegistryApi"
import RegistryForm from "./models/RegistrygForm"
import PostRegistryError from "./models/PostRegistryError"
import PostRegistryErrorCode from "./models/PostRegistryErrorCode"
import { initialState } from "./RegistrySlice"

function handleEmailColumnError({email}: PostRegistryError){
    if(email == null){
        return ""
    }
    if(email === PostRegistryErrorCode.COLUMN_EMPTY){
        return "Email cannot be empty."
    }
    if(email === PostRegistryErrorCode.EMAIL_FORMAT_INVALID){
        return "Please enter valid email."
    }
    return "Email column error."
}

function handlePasswordColumnError({password}: PostRegistryError){
    if(password == null){
        return ""   
    }
    if(password === PostRegistryErrorCode.COLUMN_EMPTY){
        return "Password cannot be empty."
    }
    if(password === PostRegistryErrorCode.PASSWORD_LESS_THAN_8_CHARS){
        return "Password should have at least 8 characters."
    }
    return "Password column error."
}

function handlePhoneColumnError({phone}: PostRegistryError){
    if(phone == null){
        return ""
    }
    if(phone === PostRegistryErrorCode.PHONE_FORMAT_INVALID){
        return "Please input a valid phone number."
    }
    return "Phone column error."
}

function handleUsernameColumnError({username}: PostRegistryError){
    if(username == null){
        return ""
    }
    if(username === PostRegistryErrorCode.COLUMN_EMPTY){
        return "Username cannot be empty."
    }
    if(username === PostRegistryErrorCode.USERNAME_CONTAINS_INVALID_CHARS){
        return "Please enter a valid username."
    }
    if(username === PostRegistryErrorCode.USERNAME_LESS_THAN_2_CHARS){
        return "Username should have at least 2 characters."
    }
    if(username === PostRegistryErrorCode.USERNAME_EXISTS){
        return "Username exists. Please use another one."
    }
    return "Username column error."
}

function handleRegistryError(error: AxiosError<PostRegistryError>){
    const httpStatus = error.response?.status
    const initialErrorState = initialState.postRegistryResponse.error
    if(httpStatus === HttpStatusCode.UnprocessableEntity){
        const errorResponse = error.response?.data
        if(!errorResponse) return initialErrorState
        const username = handleUsernameColumnError(errorResponse)
        const password = handlePasswordColumnError(errorResponse)
        const email = handleEmailColumnError(errorResponse)
        const phone = handlePhoneColumnError(errorResponse)
        return { username, password, email, phone }
    }
    return initialErrorState
}

function* postRegistry(form: RegistryForm){
    yield call(RegistryApi, form)
    yield put(RegistryAction.succeed())
}

function* tryToPostRegistry({payload}: PayloadAction<RegistryForm>){
    const {start, end, fail} = RegistryAction
    yield put(start())
    try{
        yield call(postRegistry, payload)
    }catch(ex: any){
        const errorMessages = handleRegistryError(ex)
        yield put(fail(errorMessages))
    }finally{
        yield put(end())
    }
}

export default function* postRegistrySaga(){
    yield takeEvery(RegistryAction.async, tryToPostRegistry)
}