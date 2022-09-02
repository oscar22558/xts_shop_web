import { takeEvery, put, call, select } from "@redux-saga/core/effects"
import { PayloadAction } from "@reduxjs/toolkit"
import { AxiosError } from "axios"
import AuthenticationSelector from "../../authentication/AuthenticationSelector"
import HttpStatusCode from "../../models/HttpStatusCode"
import { RootState } from "../../Store"
import UpdatePasswordError from "../models/UpdatePasswordError"
import UpdatePasswordErrorCode from "../models/UpdatePasswordErrorCode"
import UpdatePasswordForm from "../models/UpdatePasswordForm"
import { UpdatePasswordAction } from "../UserAction"
import { UpdatePasswordApi } from "../UserApi"

function* updatePassword(updatePasswordForm: UpdatePasswordForm){
    yield call(UpdatePasswordApi, updatePasswordForm)
    yield put(UpdatePasswordAction.succeed())
}

function handleForbiddenError(token: string|null){
    return token 
        ? {column: "password", error: "Password incorrect."}
        : {column: "", error: "Please sign-in first."}

}

function handleUnprocessableEntity(errorResponse: any){
    const {password: passwordError, newPassword: newPasswordError}= errorResponse
    if(passwordError === UpdatePasswordErrorCode.PASSWORD_CANNOT_BE_BLANK){
        return {column: "password", error: "Please enter original password."}
    }
    if(newPasswordError === UpdatePasswordErrorCode.PASSWORD_CANNOT_BE_BLANK){
        return {column: "newPassword", error: "Password cannot be blank."}
    }
    if(newPasswordError === UpdatePasswordErrorCode.LENGTH_AT_LEAST_8){
        return {column: "newPassword", error: "Password should have at least 8 characters."}
    }
    return {column: "", error: "Error in updating password."}
}

function* handleError(ex?: AxiosError<any>){
    const statusCode = ex?.response?.status
    if(statusCode === HttpStatusCode.Forbidden){
        const authenticationToekn: RootState["authentication"] = yield select(AuthenticationSelector)
        return handleForbiddenError(authenticationToekn.authentication.data.token)
    }
    if(statusCode === HttpStatusCode.UnprocessableEntity){
        const errorResponse = ex?.response?.data
        return handleUnprocessableEntity(errorResponse)
        
    }
    return {column: "", error: "Error in updating password."}
}

function* tryToUpdatePassword(action: PayloadAction<UpdatePasswordForm>){
    const {start, end, fail} = UpdatePasswordAction
    yield put(start())
    try{
        const updatePasswordForm = action.payload
        yield call(updatePassword, updatePasswordForm)
    }catch(ex: any){
        const errorMessage: UpdatePasswordError = yield call(handleError, ex)
        yield put(fail(errorMessage))
    }finally{
        yield put(end())
    }
}

function* updatePasswordSaga(){
    yield takeEvery(UpdatePasswordAction.async, tryToUpdatePassword)
}

export default updatePasswordSaga