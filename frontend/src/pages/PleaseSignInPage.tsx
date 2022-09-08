import { Link } from "@mui/material"
import AppRouteList from "../routes/AppRouteList"

const PleaseSignInPage = ()=>{
    return <div>
        Please <Link href={AppRouteList.signIn}>sign in</Link> first.
    </div>
}
export default PleaseSignInPage