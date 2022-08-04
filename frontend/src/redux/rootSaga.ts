import createSagaMiddleware, {Saga} from "redux-saga";
import {
    getAllSaga as getAllCategoriesSaga,
    getSaga as getCategorySaga
} from "./categories/CategoriesSaga";
import {getSaga as getAllRoutesSaga} from "./api-routes/ApiRoutesSaga";
import {getAllOfSaga as getAllItemsSaga} from "./items/ItemsSaga";
import {getAllBrandsSaga as getAllBrandsSaga} from "./brands/BrandsSaga"
import {getItemsByIdSaga as getCartItemsByIdSaga} from "./cart-items/CartItemsSaga"
import {postAuthenticationSaga} from "./authentication/AuthenticationSaga"
import getUserSaga from "./user/sagas/getUserSaga"
import updateUserSaga from "./user/sagas/updateUserSaga"
import updatePasswordSaga from "./user/sagas/updatePasswordSaga";
import addAddressSaga from "./user-addresses/add-address/AddAddressSaga";
import deleteAddressSaga from "./user-addresses/delete-address/DeleteAddressSaga";

export const sagaMiddleware = createSagaMiddleware()
export const rootSaga = [
    getAllRoutesSaga,
    getAllCategoriesSaga,
    getCategorySaga,
    getAllItemsSaga,
    getAllBrandsSaga,
    getCartItemsByIdSaga,
    postAuthenticationSaga,
    getUserSaga,
    updateUserSaga,
    updatePasswordSaga,
    addAddressSaga,
    deleteAddressSaga
]
export const runSagas = (sagas: Saga[])=>{
    sagas.forEach((saga)=>sagaMiddleware.run(saga))
}

