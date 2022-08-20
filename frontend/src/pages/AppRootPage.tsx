import { Outlet } from "react-router-dom"
import AppTopBar from '../components/TopBar/AppTopBar';
import { Box } from "@mui/material"

const AppRootPage = ()=>{
    return <Box sx={{height: "100%", display: "flex", flexDirection: "column"}}>
        <AppTopBar />
        <Outlet />
    </Box>
}
export default AppRootPage