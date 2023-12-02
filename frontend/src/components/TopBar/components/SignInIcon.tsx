import { Link } from "@mui/material"
import AppRouteList from "../../../routes/AppRouteList"

const SignInIcon = ()=>{
    return <Link href={AppRouteList.signIn} color="#fff" underline="none">
        Sign-In
    </Link>
}

export default SignInIcon