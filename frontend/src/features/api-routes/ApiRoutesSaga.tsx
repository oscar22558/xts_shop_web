import {call, put, takeEvery} from "@redux-saga/core/effects";
import ApiRoutesActions from "./ApiRoutesAction";
import ApiRoutesApi from "./ApiRoutesApi"

export function* get(): Generator<any, any, any>{
    const { start, end, success, fail } = ApiRoutesActions.get
    yield put(start())
    try{
        const { data } = yield call(ApiRoutesApi.get)
        yield put(success(data))
    }catch (ex: any) {
        console.error(ex)
        yield put(fail(ex.message))
    }finally {
        yield put(end())
    }
}

export function* getSaga(){
    yield takeEvery(ApiRoutesActions.get.async, get)
}
