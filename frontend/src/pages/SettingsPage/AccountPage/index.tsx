import { Box, Button, Grid, Typography } from "@mui/material"
import AccountSection from "./AccountSection"
import PasswordSection from "./PasswordSection"


const SettingsPage = ()=>{
    return <Box sx={{paddingTop: "30px"}}>
        <AccountSection />
        <PasswordSection />
    </Box>
}
export default SettingsPage