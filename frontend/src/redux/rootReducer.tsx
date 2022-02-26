import {combineReducers} from "redux";
import categories from "./categories/reducer"
import routes from "./apiRoutes/reducer"
import items from "./items/reducer"
const rootReducer = combineReducers({
    routes,
    categories,
    items
})
export default rootReducer