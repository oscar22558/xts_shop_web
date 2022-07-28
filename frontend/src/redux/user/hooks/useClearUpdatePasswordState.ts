import { useCallback } from "react"
import { useAppDispatch } from "../../Hooks"
import { UpdatePasswordAction } from "../UserAction"

const useClearUpdatePasswordState = ()=>{
    const dispatch = useAppDispatch()
    return ()=>{
        dispatch(UpdatePasswordAction.end)
        dispatch(UpdatePasswordAction.clearError)
    }
}
export default useClearUpdatePasswordState