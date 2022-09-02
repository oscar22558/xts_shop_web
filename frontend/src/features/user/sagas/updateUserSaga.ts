import { takeEvery, put, call } from "@redux-saga/core/effects"
import { PayloadAction } from "@reduxjs/toolkit";
import { AxiosResponse } from "axios";
import User from "../models/User";
import { UpdateUserAction } from "../UserAction";
import { UpdateUserApi } from "../UserApi";

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
        yield put(fail(ex?.message ?? ""))
    }finally{
        yield put(end())
    }
}

function* updateUserSaga(){
    yield takeEvery(UpdateUserAction.async, tryUpdateUser)
}
export default updateUserSaga