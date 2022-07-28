import { takeEvery, put, call } from "@redux-saga/core/effects"
import { PayloadAction } from "@reduxjs/toolkit"
import UpdatePasswordForm from "../models/UpdatePasswordForm"
import { UpdatePasswordAction } from "../UserAction"
import { UpdatePasswordApi } from "../UserApi"

function* updatePassword(updatePasswordForm: UpdatePasswordForm){
    yield call(UpdatePasswordApi, updatePasswordForm)
}

function* tryToUpdatePassword(action: PayloadAction<UpdatePasswordForm>){
    const {start, end, fail} = UpdatePasswordAction
    yield put(start())
    try{
        const updatePasswordForm = action.payload
        yield call(updatePassword, updatePasswordForm)
    }catch(ex: any){
        yield put(fail(ex?.message))
    }finally{
        yield put(end())
    }
}

function* updatePasswordSaga(){
    yield takeEvery(UpdatePasswordAction.async, tryToUpdatePassword)
}

export default updatePasswordSaga