import createSagaMiddleware, {Saga} from "redux-saga";
import {
    getAllSaga as getAllCategoriesSaga,
    getSaga as getCategorySaga
} from "./categories/CategoriesSaga";
import {getSaga as getAllRoutesSaga} from "./api-routes/ApiRoutesSaga";
import {getAllOfSaga as getAllItemsSaga} from "./items/ItemsSaga";
import {getAllBrandsSaga as getAllBrandsSaga} from "./brands/BrandsSaga"
import {getItemsByIdSaga as getCartItemsByIdSaga} from "./cart-items/CartItemsSaga"

export const sagaMiddleware = createSagaMiddleware()
export const rootSaga = [
    getAllRoutesSaga,
    getAllCategoriesSaga,
    getCategorySaga,
    getAllItemsSaga,
    getAllBrandsSaga,
    getCartItemsByIdSaga
]
export const runSagas = (sagas: Saga[])=>{
    sagas.forEach((saga)=>sagaMiddleware.run(saga))
}

