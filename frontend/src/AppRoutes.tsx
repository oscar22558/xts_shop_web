import {jsx} from "@emotion/react"
import { BrowserRouter, Route, Routes } from "react-router-dom"
import Home from "./pages/home"
import CategoryList from "./pages/categories/ItemsInCategory.page"
import AppTopBar from "./views/TopBar/AppTopBar"
import CategoriesTabs from "./views/CategoriesTab/CategoriesTabs"
import { Container } from "@mui/material"
const AppRoutes = ()=>{
    return (<>
        <AppTopBar />
        <Container maxWidth="xl">
            <img src="" style={{height: "200px", width: "100%"}}></img>
            <CategoriesTabs /> 
            <Routes>
                <Route path="/" element={<Home />}/>
                <Route path="/categories/*" element={<CategoryList />}/>
            </Routes>
        </Container>
    </>)
}
export default AppRoutes