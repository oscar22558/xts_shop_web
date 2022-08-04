import { takeEvery, put, call } from "@redux-saga/core/effects"
import { PayloadAction } from "@reduxjs/toolkit"
import GetUserAction from "../../user/UserAction"
import AddAddressesApi from "./AddAddressApi"
import AddAddressForm from "../models/AddAddressForm"
import UserAddressAction from "../UserAddressesAction"

function* addAddress(addAddressForm: AddAddressForm){
    yield call(AddAddressesApi, addAddressForm)
    yield put(GetUserAction.async())
}

function* tryToAddAddress(action: PayloadAction<AddAddressForm>){
    const {start, end, fail} = UserAddressAction.addAddress
    yield put(start())
    try{
        yield call(addAddress, action.payload)
    }catch(ex: any){
        yield put(fail(ex?.message))
    }finally{
        yield put(end())
    }
}

export function* addAddressSaga(){
    yield takeEvery(UserAddressAction.addAddress.async, tryToAddAddress)
}
export default addAddressSaga