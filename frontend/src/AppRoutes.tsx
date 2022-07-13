import { BrowserRouter, Route, Routes } from "react-router-dom"
import AppRootPage from "./pages/AppRootPage"
import CartPage from "./pages/CartPage"
import ItemsPage from "./pages/ItemsPage"
import HomePage from "./pages/ItemsPage/HomePage"
import ItemsInCateogryPage from "./pages/ItemsPage/ItemsInCategoryPage"
import SignInPage from "./pages/SignInPage"

const AppRoutes = ()=>{
    return (
        <BrowserRouter>
            <Routes>
                <Route path="" element={<AppRootPage />}>
                    <Route path="" element={<ItemsPage />}>
                        <Route index element={<HomePage />} />
                        <Route path="categories/*" element={<ItemsInCateogryPage />}/>
                    </Route>
                    <Route path="cart" element={<CartPage />}/>
                    <Route path="sign-in" element={<SignInPage />}/>
                </Route>
            </Routes>
        </BrowserRouter>
    )
}
export default AppRoutes