import { Box, Grid } from "@mui/material"
import { Outlet } from "react-router-dom"
import SettingMenu from "./SettingMenu"

const SettingsPage = ()=>{
    return <Box sx={{paddingTop: "30px"}}>
        <Grid container>
            <Grid item xs={3}>
                <SettingMenu />
            </Grid>
            <Grid item xs={9} sx={{paddingLeft: "50px"}}>
                <Outlet />
            </Grid>
        </Grid>
    </Box>
}
export default SettingsPage