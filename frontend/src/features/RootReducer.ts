import {combineReducers} from "redux";
import categories from "./categories/CategoriesReducer"
import routes from "./api-routes/ApiRoutesReducer"
import items from "./items/ItemsReducer"
import { ItemDetailReducer as itemDetail } from "./item-detail/ItemDetailSlice";
import brands from "./brands/BrandsReducer"
import cartItems from "./cart-items/CartItemsReducer"
import authentication from "./authentication/AuthenticationReducer"
import order from "./order/OrderReducer"
import {OrderPaymentReducer as orderPayment} from "./order/payment/OrderPaymentSlice"
import user from "./user/UserReducer"
import userAddresses from "./user-addresses/UserAddressesReducer";
import { InvoiceReducer as invoice } from "./cart-items/invoice/InvoiceSlice"
import {RegistryReducer as registry} from "./registry/RegistrySlice"

const CombinedReducers = {
    routes,
    categories,
    items,
    itemDetail,
    brands,
    cartItems,
    authentication,
    order,
    orderPayment,
    user,
    userAddresses,
    invoice,
    registry,
}

export type ReducerKeysType = keyof typeof CombinedReducers

const RootReducer = combineReducers(CombinedReducers)
export default RootReducer
