import { BrowserRouter, Route, Routes } from "react-router-dom"
import AuthenticationSelector from "../features/authentication/AuthenticationSelector"
import { useAppSelector } from "../features/Hooks"

import AppRootPage from "../pages/AppRootPage"
import CartPage from "../pages/CartPage"
import FooterPage from "../pages/FooterPage"
import ItemPage from "../pages/ItemPage"
import ItemsPage from "../pages/ItemsPage"
import HomePage from "../pages/ItemsPage/HomePage"
import ItemsInCateogryPage from "../pages/ItemsPage/ItemsInCategoryPage"
import MiniFooterPage from "../pages/MiniFooterPage"
import OrderDetailPage from "../pages/OrderDetailPage"
import OrderRecipientPage from "../pages/CheckoutPages/OrderRecipientPage"
import OrdersPage from "../pages/OrdersPage/OrdersPage"
import PaymentPage from "../pages/CheckoutPages/PaymentPage"
import PaymentSuccessPage from "../pages/CheckoutPages/PaymentSuccessPage"
import PleaseSignInPage from "../pages/PleaseSignInPage"
import SettingsPage from "../pages/SettingsPage"
import AccountPage from "../pages/SettingsPage/AccountPage"
import AddressPage from "../pages/SettingsPage/AddressPage"
import SignInPage from "../pages/SignInPage"
import AppRouteList from "./AppRouteList"
import CheckoutPages from "../pages/CheckoutPages"

const AppRoutes = ()=>{
    const authToken = useAppSelector(AuthenticationSelector).authentication.data.token
    return (
        <BrowserRouter>
            <Routes>
                <Route path="" element={<AppRootPage />}>
                    <Route path="" element={<FooterPage />}>
                        <Route path="" element={<ItemsPage />}>
                            <Route index element={<HomePage />} />
                            <Route path={AppRouteList.itemsInCaregory+"/*"} element={<ItemsInCateogryPage />}/>
                        </Route>
                        <Route path={AppRouteList.item.route+"/*"} element={<ItemPage />}/>
                        <Route path={AppRouteList.signIn} element={<SignInPage />}/>
                        {authToken ? (
                            <>
                            
                                <Route path={AppRouteList.settings.index} element={<SettingsPage />}>
                                    <Route path={AppRouteList.settings.account} element={<AccountPage />} />
                                    <Route path={AppRouteList.settings.addresses} element={<AddressPage />} />
                                </Route>
                            </>
                        ): <Route path="*" element={<PleaseSignInPage />}/>}

                    </Route>
                    <Route path="" element={<MiniFooterPage />}>
                        <Route path={AppRouteList.cart} element={<CartPage />}/>
                        {authToken ? (
                            <>
                                <Route path={AppRouteList.orderRecipient} element={<CheckoutPages />}/>
                                <Route path={AppRouteList.orders} element={<OrdersPage />}/>
                                <Route path={AppRouteList.order.route} element={<OrderDetailPage />}/>
                                <Route path={AppRouteList.paymentConfirmation} element={<PaymentSuccessPage />}/>
                            </>
                        ): <Route path="*" element={<PleaseSignInPage />}/>}
                    </Route>
                </Route>
            </Routes>
        </BrowserRouter>
    )
}
export default AppRoutes