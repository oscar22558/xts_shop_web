import { Box, TextField, Button } from "@mui/material"
import React, { useState } from "react"

export default ()=>{
    const [emailInput, setEmailInput] = useState("")
    const [usernameInput, setUsernameInput] = useState("")
    const [passwordInput, setPasswordInput] = useState("")

    const handleUsernameInputChange = ({target}: React.ChangeEvent<HTMLTextAreaElement>)=>{
        setUsernameInput(target.value)
    }

    const handlePasswordInputChange = ({target}: React.ChangeEvent<HTMLTextAreaElement>)=>{
        setPasswordInput(target.value)
    }

    const handleEmailInputChange = ({target}: React.ChangeEvent<HTMLTextAreaElement>)=>{
        setEmailInput(target.value)
    }

    return (
        <Box display="flex" justifyContent="center" padding="10px" border="1px solid" alignItems="center" flexDirection="column">
            <Box sx={{paddingY: "10px"}}>Create a account</Box>
            <TextField title="username" value={usernameInput} onChange={handleUsernameInputChange}/>
            <TextField title="email" value={emailInput} onChange={handleEmailInputChange}/>
            <TextField title="password" type="password" value={passwordInput} onChange={handlePasswordInputChange}/>
            <Button title="Create">Create</Button>
        </Box>
    )
}