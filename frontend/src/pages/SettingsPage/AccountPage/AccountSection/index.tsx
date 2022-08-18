import { useState } from "react"
import { Box, Button, Divider, Grid, Typography, useScrollTrigger } from "@mui/material"
import useUser from "../../../../redux/user/hooks/useUser"
import EditAccountForm from "./EditAccountForm"

const AccountSection = ()=>{
    const {user} = useUser()
    const [isEditFormShown, setIsEditFormShown] = useState(false)
    const rowContentCss = {padding: "10px", height: "56px", display: "flex", flexDirection: "row"}
    const labelCss = {flex: 25, display: "flex", alignItems: "center"}
    const valueCss= {flex: 75, display: "flex", alignItems: "center"}
    const viewModel = [
        {
            label: "Username",
            value: user.username
        },
        {
            label: "Email",
            value: user.email
        },
        {
            label: "Phone",
            value: user.phone
        }
    ]

    const hideEditForm = ()=>setIsEditFormShown(false)

    const handleEditClick = ()=>setIsEditFormShown(true)
    
    const handleCancelClick = hideEditForm

    return <>
        <Grid container>
            <Grid item xs>
                <Typography variant="h4">Account</Typography>
            </Grid>
            <Grid item xs sx={{display: "flex", justifyContent: "flex-end", alignItems: "flex-end"}}>
                {isEditFormShown 
                    ? <Button onClick={handleCancelClick}>Cancel</Button>
                    : <Button onClick={handleEditClick}>Edit</Button>
                }
            </Grid>
        </Grid>
        <Box sx={{paddingTop: "20px"}}>
        {
            isEditFormShown ? <EditAccountForm initialData={user} handleFinishEditing={hideEditForm}/> :(
                <>
                    {viewModel.map((itemData, index)=>(
                        <Box key={index}>
                            <Box sx={rowContentCss}>
                                <Box sx={labelCss}>{itemData.label}</Box>
                                <Box sx={valueCss}>{itemData.value}</Box>
                            </Box>
                            <Divider />
                        </Box>
                    ))}
                    <Box sx={{height: "100px"}}></Box>
                </>
            )
        }
        </Box>
    </>
}
export default AccountSection