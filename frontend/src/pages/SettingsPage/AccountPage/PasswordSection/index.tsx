import { Button, Grid, Typography } from "@mui/material"
import { useState } from "react"
import EditPasswordForm from "./EditPasswordForm"

const PasswordSection = ()=>{
    const [isEditPasswordFormShown, setIsEditPasswordFormShown] = useState(false)

    const hideEditPasswordForm = ()=>setIsEditPasswordFormShown(false)
    const showEditPasswordForm = ()=>setIsEditPasswordFormShown(true)

    const handleUpdateBtnClick = showEditPasswordForm
    const handleCancelBtnClick = hideEditPasswordForm
    const handleUserFinishedUpdate = hideEditPasswordForm

    return (<Grid container>
        <Grid container>
            <Grid item xs>
                <Typography variant="h4">Password</Typography>
            </Grid>
            <Grid item xs sx={{display: "flex", justifyContent: "flex-end", alignItems: "flex-end"}}>
            {
                isEditPasswordFormShown 
                ? <Button onClick={handleCancelBtnClick}>Cancel</Button> 
                : <Button onClick={handleUpdateBtnClick}>Update</Button>
            }
            </Grid>
        </Grid>
        <Grid container>
        {
            isEditPasswordFormShown
            ? <EditPasswordForm onUserFinishedUpdate={handleUserFinishedUpdate}/>
            : undefined
        }
        </Grid>
    </Grid>)
}
export default PasswordSection