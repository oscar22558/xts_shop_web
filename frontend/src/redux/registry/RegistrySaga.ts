import {put, call, takeEvery} from "@redux-saga/core/effects"
import { PayloadAction } from "@reduxjs/toolkit"
import RegistryAction from "./RegistryAction"
import RegistryApi from "./RegistryApi"
import RegistryForm from "./RegistrygForm"

function* postRegistry(form: RegistryForm){
    yield call(RegistryApi, form)
}

function* tryToPostRegistry({payload}: PayloadAction<RegistryForm>){
    const {start, end, fail} = RegistryAction
    yield put(start())
    try{
        yield call(postRegistry, payload)
    }catch(ex: any){
        yield put(fail("Registry Error"))
    }finally{
        yield put(end())
    }
}

export default function* postRegistrySaga(){
    yield takeEvery(RegistryAction.async, tryToPostRegistry)
}