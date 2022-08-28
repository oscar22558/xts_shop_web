import { Box, TextField, Button } from "@mui/material"
import React, { useState } from "react"
import { useAppDispatch } from "../../../redux/Hooks"
import RegistryAction from "../../../redux/registry/RegistryAction"
import RegistryForm from "../../../redux/registry/RegistrygForm"

export default ()=>{
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
        <Box display="flex" justifyContent="center" padding="10px" border="1px solid" alignItems="center" flexDirection="column">
            <Box sx={{paddingY: "10px"}}>Create a account</Box>
            <TextField
                name="username"
                title="username" 
                label="username" 
                value={registryForm.username} 
                onChange={handleChange}
            />
            <TextField
                name="email" 
                title="email" 
                label="email" 
                value={registryForm.email} 
                onChange={handleChange}
            />
            <TextField
                name="password"  
                title="password" 
                label="password" 
                type="password" 
                value={registryForm.password} 
                onChange={handleChange}
            />
            <TextField
                name="phone" 
                title="phone" 
                label="phone"
                value={registryForm.phone} 
                onChange={handleChange}
            />
            <Button title="Create" onClick={handleSubmit}>Create</Button>
        </Box>
    )
}