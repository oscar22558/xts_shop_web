import { Box, TextField, Button, FormHelperText } from "@mui/material"
import React, { useState } from "react"
import { useAppDispatch, useAppSelector } from "../../../features/Hooks"
import RegistryAction from "../../../features/registry/RegistryAction"
import RegistryForm from "../../../features/registry/models/RegistrygForm"
import RegistrySelector from "../../../features/registry/RegistrySelector"
import PasswordInputField from "../../SettingsPage/AccountPage/PasswordSection/EditPasswordForm/PasswordInputField"

const RegistrySection = ()=>{
    const dispatch = useAppDispatch()
    const {
        email: emailColumnError,
        username: usernameColumnError,
        phone: phoneColumnError,
        password: passwordColumnError
    } = useAppSelector(RegistrySelector).postRegistryResponse.error
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

    const viewModels = [
        {label: "username", isError: usernameColumnError !== "", errorMessage: usernameColumnError, value: registryForm.username},
        {label: "email", isError: emailColumnError !== "", errorMessage: emailColumnError, value: registryForm.email},
        {label: "phone", isError: phoneColumnError !== "", errorMessage: phoneColumnError, value: registryForm.phone},
    ]

    return (
        <Box display="flex" justifyContent="flex-start" paddingX="50px" alignItems="center" flexDirection="column" height="400px">
            <Box sx={{height: "50px", paddingY: "10px"}}>New to our online shop?</Box>
            {viewModels.map(({label, errorMessage, isError, value}, index)=>(
                <Box key={index} display="flex" flexDirection="column" marginBottom="15px">
                    <TextField
                        name={label}  
                        type={label} 
                        title={label}
                        label={label}
                        error={isError}
                        sx={{width: "275px"}}
                        value={value} 
                        onChange={handleChange}
                    />
                    {isError && <FormHelperText error>{errorMessage}</FormHelperText>}
                </Box>
            ))}
            <PasswordInputField
                error={passwordColumnError !== ""}
                value={registryForm.password} 
                onChange={handleChange}
                sx={{width: "275px"}}
            />

            <Button title="Create" onClick={handleSubmit}>Create Account</Button>
        </Box>
    )
}
export default RegistrySection