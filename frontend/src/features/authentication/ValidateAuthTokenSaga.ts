import {takeEvery, put, call, select} from "@redux-saga/core/effects"
import { RootState } from "../Store"
import { DestroyAuthenticationAction, ValidateAuthTokenAction } from "./AuthenticationAction"
import AuthenticationSelector from "./AuthenticationSelector"
import ValidateAuthTokenApi from "./ValidateAuthTokenApi"


function* validateAuthToken(){
    const {start, end, fail} = ValidateAuthTokenAction
    const {authentication}: RootState["authentication"] = yield select(AuthenticationSelector)
    const authToken = authentication.data
    if(!authToken){
        yield put(end())
        return
    }

    yield put(start())
    try{
        yield call(ValidateAuthTokenApi)
    }catch(ex: any){
        yield put(fail())
        yield put(DestroyAuthenticationAction())
    }finally{
        yield put(end())
    }
}
function* validateAuthTokenSaga(){
    yield takeEvery(ValidateAuthTokenAction.async, validateAuthToken)
}
export default validateAuthTokenSaga