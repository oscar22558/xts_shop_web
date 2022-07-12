import { useAppDispatch, useAppSelector} from "../redux/Hooks"
import routesSelector from "../redux/api-routes/ApiRoutesSelector"
import BrandsAction from "../redux/brands/BrandsAction"

const useFetchBrands = ()=>{
    const { data } = useAppSelector(routesSelector).get
    const dispatch = useAppDispatch()
    return ()=>{ if(data) dispatch(BrandsAction.getAllBrands.async()) }
}
export default useFetchBrands