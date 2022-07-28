import { Outlet } from "react-router-dom"
import CategoriesTabs from "../../components/CategoriesTab/CategoriesTabs"
import TopBanner from "../../components/TopBanner/TopBanner"

const ItemsPage = ()=>{
    return <>
        <TopBanner />
        <CategoriesTabs /> 
        <Outlet />
    </>
}
export default ItemsPage