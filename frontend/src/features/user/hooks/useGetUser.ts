import { useEffect } from "react"
import AuthenticationSelector from "../../authentication/AuthenticationSelector"
import { useAppDispatch, useAppSelector } from "../../Hooks"
import GetUserAction from "../UserAction"

const useGetUser = ()=>{
    const dispatch = useAppDispatch()
    const authToken = useAppSelector(AuthenticationSelector).authentication.data.token
    useEffect(()=>{
        if(authToken){
            dispatch(GetUserAction.async()) 
        }
    }, [authToken])
}
export default useGetUser