import { useNavigate } from "react-router-dom"
import AppRouteList from "../../../routes/AppRouteList"

const SignInIcon = ()=>{
    const navigate = useNavigate()
    const handleClick = ()=>{
        navigate(AppRouteList.signIn)
    }

    return <div onClick={handleClick}>
        Sign-In
    </div>
}

export default SignInIcon