import {call, put, takeEvery} from "@redux-saga/core/effects";
import {
    getEnd,
    getFail,
    getSuccess,
    getStart, getAsync
} from "./action";
import api from "./api"

export function* get(): Generator<any, any, any>{
    yield put(getStart())
    try{
        console.log("Getting Routes......")
        const { data } = yield call(api.get)
        console.log("routes:")
        console.log(data)
        yield put(getSuccess(data))
    }catch (ex: any) {
        console.error(ex)
        yield put(getFail(ex.message as string))
    }finally {
        yield put(getEnd())
    }
}

export function* getSaga(){
    yield takeEvery(getAsync, get)
}
