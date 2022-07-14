import {takeEvery} from "@redux-saga/core/effects"
import { PlaceOrderAction } from "./OrderAction"


function* tryToPostOrder(){

}

function* postOrdetrSaga(){
    yield takeEvery(PlaceOrderAction.async, tryToPostOrder)
}
export default postOrdetrSaga