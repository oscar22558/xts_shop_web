import { Link } from "@mui/material"
import { useNavigate } from "react-router-dom"
import AppRouteList from "../../../routes/AppRouteList"

const HomePageLogoBtn = ()=>{
    return <Link fontSize="23px" color="#fff" href={AppRouteList.home} underline="none">LOGO</Link>
}
export default HomePageLogoBtn