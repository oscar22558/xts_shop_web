import { useAppDispatch } from "../../Hooks"
import User from "../models/User"
import { UpdateUserAction } from "../UserAction"

const useUpdateUser = ()=>{
    const dispatch = useAppDispatch()
    return (updatedUser: User)=>{
        dispatch(UpdateUserAction.async(updatedUser))
    }
}
export default useUpdateUser