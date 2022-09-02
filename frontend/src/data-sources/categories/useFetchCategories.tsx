import {useAppDispatch, useAppSelector} from "../../features/Hooks";
import CategoriesActions from "../../features/categories/CategoriesAction";
import routesSelector from "../../features/api-routes/ApiRoutesSelector"

const useFetchCategories = () => {
    const { data } = useAppSelector(routesSelector).get
    const appDispatch = useAppDispatch()
    return ()=>{ if(data) appDispatch(CategoriesActions.getAllCategories.async()) }
}
export default useFetchCategories