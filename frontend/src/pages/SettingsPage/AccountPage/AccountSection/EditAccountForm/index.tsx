import { Box, Button, Divider, TextField } from "@mui/material"
import React, { useEffect, useState } from "react"
import { useAppDispatch, useAppSelector } from "../../../../../redux/Hooks"
import useUpdateUser from "../../../../../redux/user/hooks/useUpdateUser"
import User from "../../../../../redux/user/models/User"
import { UpdateUserAction } from "../../../../../redux/user/UserAction"
import UserSelector from "../../../../../redux/user/UserSelector"

type Props = {
    initialData: User
    handleFinishEditing?: ()=>void
}

const initialColumnValidationState = {
    username: true,
    email: true,
    phone: true
}

const rowContentCss = {padding: "10px", display: "flex", flexDirection: "row", justifyContent: "center", alignItems: "center"}

const labelCss = {flex: 25}

const textFieldCss= {flex: 75, padding: "0px"}

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

    const validateUsername = (username: string)=>{
        const isValid = true
        setColumnValidation({...columnValidation, username: isValid})
    }

    const validateEmail = (email: string)=>{        
        const isValid = true
        setColumnValidation({...columnValidation, email: isValid})
    }

    const validatePhone = (phone: string)=>{
        const isValid = true
        setColumnValidation({...columnValidation, phone: isValid})
    }

    const handleUpdateBtnClick = ()=>{
        setWaitingUserClickUpdate(false)
        sendUpdateUserPutRequest(updatedUser)
    }

    useEffect(()=>{
        if(waitingUserClickUpdate) return
        if(updateUserLoading) return
        
        if(updateUserError != null){
            setWaitingUserClickUpdate(true)
            console.error("update user error")
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
            textFieldLabel: (columnValidation.username ? undefined : "Error"),
            value: updatedUser.username,
            error: !columnValidation.username,
            handleChange: (username: string)=>{
                validateUsername(username)
                setUpdatedUser({...updatedUser, username})
            }
        },
        {
            label: "Email",
            textFieldLabel: (columnValidation.email ? undefined : "Error"),
            value: updatedUser.email,
            error: !columnValidation.email,
            handleChange: (email: string)=>{
                validateEmail(email)
                setUpdatedUser({...updatedUser, email})
            }
        },
        {
            label: "Phone",
            textFieldLabel: (columnValidation.phone ? undefined : "Error"),
            value: updatedUser.phone,
            error: !columnValidation.phone,
            handleChange: (phone: string)=>{
                validatePhone(phone)
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
                        <TextField 
                            error={itemData.error}
                            label={itemData.textFieldLabel}
                            sx={textFieldCss} 
                            value={itemData.value} 
                            onChange={(event: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>)=>(
                                itemData.handleChange(event.target.value)
                            )}
                            helperText={undefined}
                        />
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