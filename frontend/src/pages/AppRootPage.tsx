import { Outlet } from "react-router-dom"
import AppTopBar from '../components/TopBar/AppTopBar';
import { Box } from "@mui/material"
import useGetUser from "../features/user/hooks/useGetUser";

const AppRootPage = ()=>{
    useGetUser()
    return <Box sx={{height: "100%", display: "flex", flexDirection: "column"}}>
        <AppTopBar />
        <Outlet />
    </Box>
}
export default AppRootPage