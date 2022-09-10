import { Box, Container } from "@mui/material"
import { Outlet } from "react-router-dom"
import MiniFooter from "../components/MiniFooter"

const MiniFooterPage = ()=>{
    return <Box sx={{flex: 1, display: "flex", flexDirection: "column"}}>
        <Container maxWidth="xl" sx={{flex: 1}}>
            <Outlet />
        </Container>
        <MiniFooter />
    </Box>
}
export default MiniFooterPage