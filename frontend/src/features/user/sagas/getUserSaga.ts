import {call, put, takeEvery} from "@redux-saga/core/effects"
import { AxiosResponse } from "axios"
import GetUserAction from "../UserAction"
import UserApi from "../UserApi"

function* getUser(){
    const response: AxiosResponse = yield call(UserApi)
    const user = response.data
    yield put(GetUserAction.succeed(user))
}

function* tryToGetUser(){
    const {start, end, fail} = GetUserAction
    yield put(start())
    try{
       yield call(getUser)
    }catch(ex: any){
        yield (fail(ex?.message))
    }finally{
        yield put(end())
    }
}

export function* getUserSaga(){
    yield takeEvery(GetUserAction.async, tryToGetUser)
}
export default getUserSaga