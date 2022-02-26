import {call, put, takeEvery} from "@redux-saga/core/effects";
import actions from "./action";
import api from "./api"

export function* get(): Generator<any, any, any>{
    const { start, end, success, fail } = actions.get
    yield put(start())
    try{
        console.log("Getting Routes......")
        const { data } = yield call(api.get)
        console.log("apiRoutes:")
        console.log(data)
        yield put(success(data))
    }catch (ex: any) {
        console.error(ex)
        yield put(fail(ex.message as string))
    }finally {
        yield put(end())
    }
}

export function* getSaga(){
    yield takeEvery(actions.get.async, get)
}
