import {combineReducers} from "redux";
import categoriesReducer from "./categories/reducer"
import routesReducer from "./routes/reducer"
const rootReducer = combineReducers({
    routes: routesReducer,
    categories: categoriesReducer
})
export default rootReducer