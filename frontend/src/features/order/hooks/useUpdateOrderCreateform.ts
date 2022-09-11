import { useAppDispatch } from "../../Hooks"
import CachedOrderCreateForm from "../models/CachedOrderCreateForm"
import { CachedOrderAction } from "../OrderAction"

const useUpdateOrderCreateForm = ()=>{
    const dispatch = useAppDispatch()
    return (newForm: CachedOrderCreateForm)=>{
        dispatch(CachedOrderAction.update({...newForm}))
    }
}
export default useUpdateOrderCreateForm