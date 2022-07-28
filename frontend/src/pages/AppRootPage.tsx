import { Outlet } from "react-router-dom"
import AppTopBar from '../components/TopBar/AppTopBar';
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