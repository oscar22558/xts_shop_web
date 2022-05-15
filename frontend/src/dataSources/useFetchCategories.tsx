import {useAppDispatch, useAppSelector} from "../redux/hooks";
import actions from "../redux/categories/action";
import routesSelector from "../redux/apiRoutes/selector"

const useFetchCategories = () => {
    const { data } = useAppSelector(routesSelector).get
    const appDispatch = useAppDispatch()
    return ()=>{ if(data) appDispatch(actions.getAll.async()) }
}
export default useFetchCategories