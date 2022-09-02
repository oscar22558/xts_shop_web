import { Box, TextField, Button } from "@mui/material"
import React, { useState } from "react"
import { useAppDispatch } from "../../../features/Hooks"
import RegistryAction from "../../../features/registry/RegistryAction"
import RegistryForm from "../../../features/registry/RegistrygForm"

const RegistrySection = ()=>{
    const dispatch = useAppDispatch()
    const [registryForm, setRegistryForm] = useState<RegistryForm>({
        username: "",
        password: "",
        email: "",
        phone: ""
    })
    
    const handleChange = ({target}: React.ChangeEvent<HTMLTextAreaElement>)=>{
        setRegistryForm({...registryForm, [target.name]: target.value})
    }
    const handleSubmit = ()=>{
        dispatch(RegistryAction.async(registryForm))
    }

    return (
        <Box display="flex" justifyContent="flex-start" paddingX="50px" alignItems="center" flexDirection="column" height="400px">
            <Box sx={{height: "50px", paddingY: "10px"}}>New to the site?</Box>
            <TextField
                name="username"
                title="username" 
                label="username" 
                sx={{marginBottom: "15px", width: "275px"}}
                value={registryForm.username} 
                onChange={handleChange}
            />
            <TextField
                name="email" 
                title="email" 
                label="email" 
                sx={{marginBottom: "15px", width: "275px"}}
                value={registryForm.email} 
                onChange={handleChange}
            />
            <TextField
                name="phone" 
                title="phone" 
                label="phone"
                sx={{marginBottom: "15px", width: "275px"}}
                value={registryForm.phone} 
                onChange={handleChange}
            />
            <TextField
                name="password"  
                title="password" 
                label="password" 
                type="password" 
                sx={{marginBottom: "15px", width: "275px"}}
                value={registryForm.password} 
                onChange={handleChange}
            />

            <Button title="Create" onClick={handleSubmit}>Create Account</Button>
        </Box>
    )
}
export default RegistrySection