import React, { useEffect, useState } from "react"

import { useNavigate } from "react-router-dom"
import { Box, Button, FormControl, FormHelperText, TextField } from "@mui/material"

import useAuthenticateUser from "../../../data-sources/authentication/useAuthenticateUser"
import useAuthentication from "../../../data-sources/authentication/useAuthentication"

import AuthenticationRequest from "../../../features/authentication/models/AuthenticationRequest"
import AuthenticationAction from "../../../features/authentication/AuthenticationAction"
import { useAppDispatch } from "../../../features/Hooks"

import AppRouteList from "../../../routes/AppRouteList"
import PasswordInputField from "../../SettingsPage/AccountPage/PasswordSection/EditPasswordForm/PasswordInputField"

const SignInPage = ()=>{
    const navigate = useNavigate()
    const dispatch = useAppDispatch()
    const authenticateUser = useAuthenticateUser()
    const authentication = useAuthentication()

    const [authenticationRequest, setAuthenticationRequest] = useState<AuthenticationRequest>({username: "", password: ""})

    const error = authentication.error
    const handleInputChange = ({target}: React.ChangeEvent<HTMLTextAreaElement>)=>{
        setAuthenticationRequest({...authenticationRequest, [target.name]: target.value})
    }

    const handleSignInBtnClick = ()=>{
        authenticateUser(authenticationRequest)
    }

    useEffect(()=>{
        const navigateToHomePage = ()=>{
            navigate(AppRouteList.home)
        }
        if(authentication.isUserAuthenticated){
            navigateToHomePage()
        }
        return ()=>{
            dispatch(AuthenticationAction.end())
            dispatch(AuthenticationAction.clearError())
        }
    }, [authentication.isUserAuthenticated, navigate, dispatch])

    return (
        <FormControl>

        <Box display="flex" justifyContent="flex-start" paddingX="50px" alignItems="center" flexDirection="column" height="400px">
            <Box sx={{height: "50px", paddingY: "10px"}}>Already have account?</Box>
            <Box sx={{marginBottom: "15px"}}>
                <TextField 
                    name="username" 
                    label="username" 
                    title="username" 
                    value={authenticationRequest.username} 
                    onChange={handleInputChange} 
                    sx={{width: "275px"}}
                />
                {error.username && <FormHelperText error>{error.username}</FormHelperText>}
            </Box>
            <Box sx={{marginBottom: "15px"}}>
                <PasswordInputField 
                    error={error.password !== ""}
                    errorText={error.password}
                    value={authenticationRequest.password} 
                    onChange={handleInputChange} 
                    sx={{width: "275px"}}
                />
            </Box>
            <Box sx={{display: "flex", justifyContent: "flex-start", width: "100%"}}>
                {error.form && <FormHelperText error>{error.form}</FormHelperText>}
            </Box>
            <Button title="Sign-In" onClick={handleSignInBtnClick}>Sign-in</Button>
        </Box>
        </FormControl>
    )
}
export default SignInPage