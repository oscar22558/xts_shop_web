import { takeEvery, put, call } from "@redux-saga/core/effects"
import { PayloadAction } from "@reduxjs/toolkit";
import { AxiosError, AxiosResponse } from "axios";
import UpdateUserErrorResponse from "../models/UpdateUserErrorResponse";
import User from "../models/User";
import UserErrorCode from "../models/UserErrorCode";
import { UpdateUserAction } from "../UserAction";
import { UpdateUserApi } from "../UserApi";

function handleUserColumnError(errorCode: string){
    if(errorCode === UserErrorCode.USERNAME_EMPTY){
        return "Username is missing."
    }
    if(errorCode === UserErrorCode.USERNAME_EXISTS){
        return "Username exists. Please use another one."
    }
    if(errorCode === UserErrorCode.USERNAME_INVALID){
        return "Username is invalid. Please use the correct format."
    }
    if(errorCode === UserErrorCode.USERNAME_TOO_SHORT){
        return "Username should have at least 2 characters."
    }
    return "Username column error."
}

function handleUserEmailError(errorCode: string){
    if(errorCode === UserErrorCode.EMAIL_EMPTY){
        return "Email is missing."
    }
    if(errorCode === UserErrorCode.EMAIL_EXISTS){
        return "Email has been used. Please use another one."
    }
    if(errorCode === UserErrorCode.EMAIL_INVALID_FORMAT){
        return "Email is invalid."
    }
    return "Email column error."
}

function handlePhoneError(errorCode: string){
    if(errorCode === UserErrorCode.PHONE_EMPTY){
        return "Phone is missing."
    }
    return "Phone column error."
}

function handleAxiosError(error: AxiosError<UpdateUserErrorResponse>){
    const errorResponse = error.response?.data
    const errorMessages = {
        username: "",
        email: "",
        phone: ""
    }
    if(errorResponse?.username != null){
        errorMessages.username = handleUserColumnError(errorResponse.username)
    }
    if(errorResponse?.email != null){
        errorMessages.email = handleUserEmailError(errorResponse.email)
    }
    if(errorResponse?.phone != null){
        errorMessages.phone = handlePhoneError(errorResponse.phone)
    }
    return errorMessages
}

function* updateUser(user: User){
    const response: AxiosResponse = yield call(UpdateUserApi, user)
    const updatedUser: User = response.data
    yield put(UpdateUserAction.succeed(updatedUser))
}

function* tryUpdateUser(action: PayloadAction<User>){
    const {start, end, fail} = UpdateUserAction
    yield put(start())
    try{
        yield call(updateUser, action.payload)
    }catch(ex: any){
        const errorMessage = handleAxiosError(ex)
        yield put(fail(errorMessage))
    }finally{
        yield put(end())
    }
}

function* updateUserSaga(){
    yield takeEvery(UpdateUserAction.async, tryUpdateUser)
}
export default updateUserSaga