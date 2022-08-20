import { Box, Container, Divider, Typography } from "@mui/material"

const Footer = ()=>{
    return <Box sx={{height: "400px", widht: "100%", background: "#1976d2", marginTop: "75px"}}>
        <Container maxWidth="xl">
            <Typography fontSize={50} paddingY="30px" color="#fff">Footer</Typography>
            <Divider color="#adadad"/>
        </Container>
    </Box>
}
export default Footer