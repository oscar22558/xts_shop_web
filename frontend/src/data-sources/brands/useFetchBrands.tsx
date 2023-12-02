import { useAppDispatch, useAppSelector} from "../../features/Hooks"
import routesSelector from "../../features/api-routes/ApiRoutesSelector"
import BrandsAction from "../../features/brands/BrandsAction"

const useFetchBrands = ()=>{
    const { data } = useAppSelector(routesSelector).get
    const dispatch = useAppDispatch()
    return ()=>{ if(data) dispatch(BrandsAction.getAllBrands.async()) }
}
export default useFetchBrands