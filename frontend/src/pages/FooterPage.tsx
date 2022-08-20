import { Box, Container } from "@mui/material"
import { Outlet } from "react-router-dom"
import Footer from "../components/Footer"

const FooterPage = ()=>{
    return <Box sx={{flex: 1, display: "flex", flexDirection: "column"}}>
        <Container maxWidth="xl" sx={{flex: 1}}>
            <Outlet />
        </Container>
        <Footer />
    </Box>
}
export default FooterPage