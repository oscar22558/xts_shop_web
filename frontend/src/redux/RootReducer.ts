import {combineReducers} from "redux";
import categories from "./categories/CategoriesReducer"
import routes from "./api-routes/ApiRoutesReducer"
import items from "./items/ItemsReducer"
import brands from "./brands/BrandsReducer"
import cartItems from "./cart-items/CartItemsReducer"
import authentication from "./authentication/AuthenticationReducer"
import order from "./order/OrderReducer"
import user from "./user/UserReducer"
import userAddresses from "./user-addresses/UserAddressesReducer";

const RootReducer = combineReducers({
    routes,
    categories,
    items,
    brands,
    cartItems,
    authentication,
    order,
    user,
    userAddresses
})
export default RootReducer