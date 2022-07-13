import { useNavigate } from "react-router-dom"

const SignInIcon = ()=>{
    const navigate = useNavigate()
    const handleClick = ()=>{
        navigate("sign-in")
    }

    return <div onClick={handleClick}>
        Sign-In
    </div>
}

export default SignInIcon