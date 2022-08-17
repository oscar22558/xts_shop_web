import {useAppDispatch, useAppSelector} from "../../redux/Hooks";
import CategoriesActions from "../../redux/categories/CategoriesAction";
import routesSelector from "../../redux/api-routes/ApiRoutesSelector"

const useFetchCategories = () => {
    const { data } = useAppSelector(routesSelector).get
    const appDispatch = useAppDispatch()
    return ()=>{ if(data) appDispatch(CategoriesActions.getAllCategories.async()) }
}
export default useFetchCategories