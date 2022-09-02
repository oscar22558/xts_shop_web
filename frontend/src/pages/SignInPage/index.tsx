import { Box } from "@mui/material"
import RegistrySection from "./RegistrySection"
import SignInSection from "./SignInSection"

const SignInPage = ()=>{
    return <Box display="flex" justifyContent="center" flexDirection="row" height="700px" alignItems="center">
        <SignInSection />
        <Box sx={{display: "flex", flexDirection: "column", justifyContent: "center", alignItems: "center", height: "400px"}}>
            <Box sx={{flex: 1, backgroundColor: "#adadad", width: "1px"}}></Box>
            <Box sx={{marginY: "10px"}}>or</Box>
            <Box sx={{flex: 1, backgroundColor: "#adadad", width: "1px"}}></Box>
        </Box>
        <RegistrySection />
    </Box>
}
export default SignInPage