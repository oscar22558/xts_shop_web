import { Box, Button, Divider, FormHelperText, TextField } from "@mui/material"
import React, { useEffect, useState } from "react"
import { useAppDispatch, useAppSelector } from "../../../../../features/Hooks"
import useUpdateUser from "../../../../../features/user/hooks/useUpdateUser"
import User from "../../../../../features/user/models/User"
import { UpdateUserAction } from "../../../../../features/user/UserAction"
import UserSelector from "../../../../../features/user/UserSelector"

type Props = {
    initialData: User
    handleFinishEditing?: ()=>void
}

const initialColumnValidationState = {
    username: "",
    email: "",
    phone: ""
}

const rowContentCss = {padding: "10px", display: "flex", flexDirection: "row", justifyContent: "center", alignItems: "center"}
const labelCss = {flex: 25}
const inputFieldContainerCss = {flex: 75, padding: "0px"}

const EditAccountPage = ({
    initialData,
    handleFinishEditing
}: Props)=>{
    const [waitingUserClickUpdate, setWaitingUserClickUpdate] = useState(true)
    const [updatedUser, setUpdatedUser] = useState<User>(initialData)
    const [columnValidation, setColumnValidation] = useState(initialColumnValidationState)
    const sendUpdateUserPutRequest = useUpdateUser()
    const dispatch = useAppDispatch()
    const {error: updateUserError, loading: updateUserLoading} = useAppSelector(UserSelector).updateUserResponse

    const validateUsername = ()=>{
        const username = updatedUser.username
        const isValid = username != null && username !== ""
        const message = isValid ? "" : "Missing username." 
        console.log("validate username");
        message && console.error(message);
        setColumnValidation(prevState=>({...prevState, username: message}))
        return isValid
    }

    const validateEmail = ()=>{        
        const email = updatedUser.email
        const isValid = email != null && email !== ""
        const message = isValid ? "" : "Missing email." 
        console.log("validate email");
        message && console.error(message);
        setColumnValidation(prevState=>({...prevState, email: message}))
        return isValid
    }

    const validatePhone = ()=>{
        const phone = updatedUser.phone
        const isValid = phone != null && phone !== ""
        const message = isValid ? "" : "Missing phone." 
        console.log("validate phone");
        message && console.error(message);
        setColumnValidation(prevState=>({...prevState, phone: message}))
        return isValid
    }

    const handleUpdateBtnClick = ()=>{
        const usernameValid = validateUsername()
        const emailValid = validateEmail()
        const phoneValid = validatePhone()
        const isFormValid = usernameValid && emailValid && phoneValid
        if(!isFormValid) return
        
        setWaitingUserClickUpdate(false)
        sendUpdateUserPutRequest(updatedUser)
    }

    useEffect(()=>{
        if(waitingUserClickUpdate) return
        if(updateUserLoading) return
        const columnError = Object.values(updateUserError).find(error=>error !== "") ?? ""
        if(columnError !== ""){
            setWaitingUserClickUpdate(true)
            return
        }
        handleFinishEditing && handleFinishEditing()
        return ()=>{
            dispatch(UpdateUserAction.clearError())
        }
    }, [waitingUserClickUpdate, updateUserError, updateUserLoading])

    const viewModel = [
        {
            label: "Username",
            value: updatedUser.username,
            error: columnValidation.username || updateUserError.username,
            handleChange: (username: string)=>{
                setUpdatedUser({...updatedUser, username})
            }
        },
        {
            label: "Email",
            value: updatedUser.email,
            error: columnValidation.email || updateUserError.email,
            handleChange: (email: string)=>{
                setUpdatedUser({...updatedUser, email})
            }
        },
        {
            label: "Phone",
            value: updatedUser.phone,
            error: columnValidation.phone || updateUserError.phone,
            handleChange: (phone: string)=>{
                setUpdatedUser({...updatedUser, phone})
            }
        }
    ]

    return (
        <>
            {viewModel.map((itemData, index)=>(
                <Box key={index}>
                    <Box sx={rowContentCss}>
                        <Box sx={labelCss}>{itemData.label}</Box>
                        <Box sx={{display: "flex", flexDirection: "column", ...inputFieldContainerCss}}>
                            <TextField 
                                error={itemData.error !== ""}
                                label={itemData.label}
                                value={itemData.value} 
                                onChange={(event: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>)=>(
                                    itemData.handleChange(event.target.value)
                                )}
                                helperText={undefined}
                            />
                            {itemData.error !== "" ? <FormHelperText error={itemData.error !== ""}>{itemData.error}</FormHelperText> : undefined}
                        </Box>
                    </Box>
                    <Divider />
                </Box>
            ))}
            <Box sx={{display: "flex", alignItems: "flex-start", justifyContent: "flex-end", height: "100px"}}>
                <Button sx={{marginTop: "10px", width: "100%"}} onClick={handleUpdateBtnClick} variant="contained">Update</Button>
            </Box>
        </>
    )
}
export default EditAccountPage