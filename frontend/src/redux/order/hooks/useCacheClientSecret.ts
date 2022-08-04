import { useAppDispatch, useAppSelector } from "../../Hooks"
import { CachedOrderAction } from "../OrderAction"
import OrderSelector from "../OrderSelector"

const useCacheClinetSecret = ()=>{
    const dispatch = useAppDispatch()
    const cachedOrder = useAppSelector(OrderSelector).cachedOrderCreateForm
    return (clientSecret: string)=>{
        dispatch(CachedOrderAction.update({...cachedOrder, clientSecret}))
    }
}
export default useCacheClinetSecret