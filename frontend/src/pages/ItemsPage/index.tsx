import { Outlet } from "react-router-dom"
import CategoriesTabs from "../../components/CategoriesTab/CategoriesTabs"
import TopBanner from "../../components/TopBanner/TopBanner"
import FetchBrands from "../../data-sources/brands/FetchBrands"
import useFetchCategories from "../../features/categories/hooks/useFetchCategories"

const ItemsPage = ()=>{
    useFetchCategories()
    return <>
        <FetchBrands />
        <TopBanner />
        <CategoriesTabs /> 
        <Outlet />
    </>
}
export default ItemsPage