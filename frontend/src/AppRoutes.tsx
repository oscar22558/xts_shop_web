import { BrowserRouter, Route, Routes } from "react-router-dom"
import Home from "./pages/home"
import CategoryList from "./pages/categories"
import AppTopBar from "./views/TopBar/AppTopBar"
const AppRoutes = ()=>{
    return (<>
        <AppTopBar />
        <Routes>
            <Route path="/" element={<Home />}/>
            <Route path="/categories/*" element={<CategoryList />}/>
        </Routes>
    </>)
}
export default AppRoutes