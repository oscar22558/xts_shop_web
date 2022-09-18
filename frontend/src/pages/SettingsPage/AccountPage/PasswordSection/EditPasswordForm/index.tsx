import { Box, Button, FormHelperText } from "@mui/material"
import React, { useEffect, useState } from "react"
import { useAppSelector } from "../../../../../features/Hooks"
import useUpdatePassword from "../../../../../features/user/hooks/useUpdatePassword"
import UpdatePasswordForm from "../../../../../features/user/models/UpdatePasswordForm"
import UserSelector from "../../../../../features/user/UserSelector"
import PasswordInputField from "./PasswordInputField"

type Props = {
    onUserFinishedUpdate?: ()=>void
}

const initialUpdatePasswordForm = {
    newPassword: "",
    password: "",
    passwordConfirmation: ""
}

const EditPasswordForm = ({
    onUserFinishedUpdate
}: Props)=>{
    const [waitingUserClickUpdate, setWaitingUserClickUpdate] = useState(true)
    const [updatePasswordForm, setUpdatePasswordForm] = useState<UpdatePasswordForm>(initialUpdatePasswordForm)
    
    const {error: updateRequestError, loading: updateRequestLoading} = useAppSelector(UserSelector).updatePasswordResponse
    
    const updatePassword = useUpdatePassword()

    const handleUpdatePasswordFormChange = ({target}: React.ChangeEvent<HTMLTextAreaElement>)=>{
        setUpdatePasswordForm({...updatePasswordForm, [target.name]: target.value})
    }

    const handleUpdateBtnClick = ()=>{
        const {newPassword} = updatePasswordForm
        setWaitingUserClickUpdate(false)
        updatePassword({...updatePasswordForm, passwordConfirmation: newPassword})
    }

    useEffect(()=>{
        if(waitingUserClickUpdate || updateRequestLoading){
            return
        }
        if(updateRequestError){
            setWaitingUserClickUpdate(true)    
            return
        }
        onUserFinishedUpdate?.()
    }, [waitingUserClickUpdate, updateRequestError, updateRequestLoading, onUserFinishedUpdate])
    
     
    return <Box flexDirection="column" display="flex">
        <form>
            <PasswordInputField 
                value={updatePasswordForm.password} 
                onChange={handleUpdatePasswordFormChange}
                error={updateRequestError != null && updateRequestError?.column === "password"}
                errorText={updateRequestError?.error}
            />
            <Box sx={{marginTop: "10px"}}>
                <PasswordInputField 
                    label="New Password"
                    title="New Password"
                    name="newPassword"
                    autoComplete="new-password"
                    value={updatePasswordForm.newPassword} 
                    onChange={handleUpdatePasswordFormChange}
                    error={updateRequestError != null && updateRequestError?.column === "newPassword"}
                    errorText={updateRequestError?.error}
                />
            </Box>
            {updateRequestError && updateRequestError.column === "" ? <FormHelperText error>{updateRequestError.error}</FormHelperText> : undefined}
            <Box sx={{marginTop: "10px"}} flexDirection="column" display="flex">
                <Button variant="contained" onClick={handleUpdateBtnClick}>Update</Button>
            </Box>
        </form>       
    </Box>
}
export default EditPasswordForm