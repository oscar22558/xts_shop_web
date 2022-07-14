import { Box, Button, TextField } from "@mui/material"
import React, { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import useAuthenticateUser from "../../data-sources/authentication/useAuthenticateUser"
import useAuthentication from "../../data-sources/authentication/useAuthentication"
import AuthenticationAction from "../../redux/authentication/AuthenticationAction"
import { useAppDispatch } from "../../redux/Hooks"
import AppRouteList from "../../routes/AppRouteList"

const SignInPage = ()=>{
    const [usernameInput, setUsernameInput] = useState("")
    const [passwordInput, setPasswordInput] = useState("")
    const navigate = useNavigate()
    const authenticateUser = useAuthenticateUser()
    const authentication = useAuthentication()
    const dispatch = useAppDispatch()
    const handleUsernameInputChange = ({target}: React.ChangeEvent<HTMLTextAreaElement>)=>{
        setUsernameInput(target.value)
    }

    const handlePasswordInputChange = ({target}: React.ChangeEvent<HTMLTextAreaElement>)=>{
        setPasswordInput(target.value)
    }

    const handleSignInBtnClick = ()=>{
        authenticateUser({username: usernameInput, password: passwordInput})
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

    return <Box display="flex" justifyContent="center">
        <Box display="flex" justifyContent="center" padding="10px" border="1px solid" alignItems="center" flexDirection="column">
            <TextField title="username" value={usernameInput} onChange={handleUsernameInputChange}/>
            <TextField title="password" value={passwordInput} onChange={handlePasswordInputChange}/>
            {<div>{authentication.error}</div>}
            <Button title="Sign-In" onClick={handleSignInBtnClick}>Sign-in</Button>
        </Box>
    </Box>
}
export default SignInPage