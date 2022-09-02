import { Box, Button, FormControl, FormHelperText, TextField } from "@mui/material"
import React, { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import AuthenticationRequest from "../../../redux/authentication/models/AuthenticationRequest"
import useAuthenticateUser from "../../../data-sources/authentication/useAuthenticateUser"
import useAuthentication from "../../../data-sources/authentication/useAuthentication"
import AuthenticationAction from "../../../redux/authentication/AuthenticationAction"
import { useAppDispatch } from "../../../redux/Hooks"
import AppRouteList from "../../../routes/AppRouteList"

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
            <Box sx={{height: "50px", paddingY: "10px"}}>Sign in</Box>
            <TextField name="username" label="username" title="username" value={authenticationRequest.username} onChange={handleInputChange} sx={{marginBottom: "15px", width: "275px"}}/>
            <TextField name="password" label="password" title="password" value={authenticationRequest.password} onChange={handleInputChange} sx={{marginBottom: "15px", width: "275px"}}/>
            {error && <FormHelperText error>{error}</FormHelperText>}
            <Button title="Sign-In" onClick={handleSignInBtnClick}>Sign-in</Button>
        </Box>
        </FormControl>
    )
}
export default SignInPage