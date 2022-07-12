import { Outlet } from "react-router-dom"
import CategoriesTabs from "../../views/CategoriesTab/CategoriesTabs"
import TopBanner from "../../views/TopBanner/TopBanner"

const ItemsPage = ()=>{
    return <>
        <TopBanner />
        <CategoriesTabs /> 
        <Outlet />
    </>
}
export default ItemsPage