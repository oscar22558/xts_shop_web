import { useAppDispatch } from "../../Hooks"
import UpdatePasswordForm from "../models/UpdatePasswordForm"
import { UpdatePasswordAction } from "../UserAction"

const useUpdatePassword = ()=>{
    const dispatch = useAppDispatch()
    return (updatePasswordForm: UpdatePasswordForm)=>{
        dispatch(UpdatePasswordAction.async(updatePasswordForm))
    }
}
export default useUpdatePassword