import { BrowserRouter, Route, Routes } from "react-router-dom"

import AppRootPage from "../pages/AppRootPage"
import CartPage from "../pages/CartPage"
import ItemsPage from "../pages/ItemsPage"
import HomePage from "../pages/ItemsPage/HomePage"
import ItemsInCateogryPage from "../pages/ItemsPage/ItemsInCategoryPage"
import OrderShippingAddressPage from "../pages/OrderShippingAddressPage"
import OrdersPage from "../pages/OrdersPage/OrdersPage"
import SettingsPage from "../pages/SettingsPage"
import AccountPage from "../pages/SettingsPage/AccountPage"
import SignInPage from "../pages/SignInPage"
import AppRouteList from "./AppRouteList"

const AppRoutes = ()=>{
    return (
        <BrowserRouter>
            <Routes>
                <Route path="" element={<AppRootPage />}>
                    <Route path="" element={<ItemsPage />}>
                        <Route index element={<HomePage />} />
                        <Route path={AppRouteList.itemsInCaregory+"/*"} element={<ItemsInCateogryPage />}/>
                    </Route>
                    <Route path={AppRouteList.cart} element={<CartPage />}/>
                    <Route path={AppRouteList.signIn} element={<SignInPage />}/>
                    <Route path={AppRouteList.orderShippingAddresses} element={<OrderShippingAddressPage />}/>
                    <Route path={AppRouteList.orders} element={<OrdersPage />}/>
                    <Route path={AppRouteList.settings.index} element={<SettingsPage />}>
                        <Route path={AppRouteList.settings.account} element={<AccountPage />} />
                        <Route path={AppRouteList.settings.addresses} element={<AccountPage />} />
                    </Route>
                </Route>
            </Routes>
        </BrowserRouter>
    )
}
export default AppRoutes