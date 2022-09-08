import {takeEvery, put, call} from "@redux-saga/core/effects"
import AuthenticationAction, { DestroyAuthenticationAction, ValidAuthTokenAction } from "./AuthenticationAction"
import ValidAuthTokenApi from "./ValidAuthTokenApi"


function* validAuthToken(){
    const {start, end, fail} = ValidAuthTokenAction
    yield put(start())
    try{
        yield call(ValidAuthTokenApi)
    }catch(ex: any){
        yield put(fail())
        yield put(DestroyAuthenticationAction)
    }finally{
        yield put(end())
    }
}
function* ValidAuthTokenSaga(){
    yield takeEvery(ValidAuthTokenAction.async, validAuthToken)
}
export default ValidAuthTokenSaga