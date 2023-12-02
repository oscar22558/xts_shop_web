import { useEffect } from "react"
import { useAppDispatch } from "../../Hooks"
import { ValidateAuthTokenAction } from "../AuthenticationAction"

const useValidateAuthToken = ()=>{
    const dispatch = useAppDispatch()
    useEffect(()=>{
       dispatch(ValidateAuthTokenAction.async()) 
    }, [dispatch])
}

export default useValidateAuthToken