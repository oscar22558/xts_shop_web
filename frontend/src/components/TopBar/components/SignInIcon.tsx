import { useNavigate } from "react-router-dom"
import AppRouteList from "../../../routes/AppRouteList"

const SignInIcon = ()=>{
    const navigate = useNavigate()
    const handleClick = ()=>{
        navigate(AppRouteList.signIn)
    }

    return <span onClick={handleClick}>
        Sign-In
    </span>
}

export default SignInIcon