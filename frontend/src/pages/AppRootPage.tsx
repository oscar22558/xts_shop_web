import { Outlet } from "react-router-dom"
import AppTopBar from '../views/TopBar/AppTopBar';
import TopBanner from '../views/TopBanner/TopBanner';
import { Container } from "@mui/material"

const AppRootPage = ()=>{
    return <>
        <AppTopBar />
        <Container maxWidth="xl">
            <Outlet />
        </Container>
    </>
}
export default AppRootPage