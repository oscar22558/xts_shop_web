import { Box, Button, FormHelperText } from "@mui/material"
import { useEffect, useState } from "react"
import { useAppSelector } from "../../../../../features/Hooks"
import useClearUpdatePasswordState from "../../../../../features/user/hooks/useClearUpdatePasswordState"
import useUpdatePassword from "../../../../../features/user/hooks/useUpdatePassword"
import UserSelector from "../../../../../features/user/UserSelector"
import PasswordInputField from "./PasswordInputField"

type Props = {
    onUserFinishedUpdate?: ()=>void
}

const EditPasswordForm = ({
    onUserFinishedUpdate
}: Props)=>{
    const [waitingUserClickUpdate, setWaitingUserClickUpdate] = useState(true)
    const [password, setPassword] = useState<string>("")
    const [newPassword, setNewPassword] = useState<string>("")
    const [passwordError, setPasswordError] = useState<string|undefined>(undefined)
    const [newPasswordError, setNewPasswordError] = useState<string|undefined>(undefined)
    
    const {error: updateRequestError, loading: updateRequestLoading} = useAppSelector(UserSelector).updatePasswordResponse
    
    const updatePassword = useUpdatePassword()
    const clearUpdatePasswordState = useClearUpdatePasswordState()

    const handleUpdateBtnClick = ()=>{
        if(password == null){
            setPasswordError("Cannot be blank.")
            return 
        }
        if(newPassword == null){
            setNewPasswordError("Cannot be blank.")
            return
        }
        setNewPasswordError(undefined)
        setWaitingUserClickUpdate(false)
        updatePassword({password, newPassword, passwordConfirmation: newPassword})
    }

    useEffect(()=>{
        if(waitingUserClickUpdate || updateRequestLoading){
            return
        }
        if(updateRequestError){
            setWaitingUserClickUpdate(true)    
            return
        }
        onUserFinishedUpdate && onUserFinishedUpdate()
        return ()=>{
            clearUpdatePasswordState()
        }
    }, [waitingUserClickUpdate, updateRequestError, updateRequestLoading, clearUpdatePasswordState, onUserFinishedUpdate])
    
    return <Box flexDirection="column" display="flex">
        <PasswordInputField 
            value={password} 
            onChange={setPassword}
            error={updateRequestError != null && updateRequestError?.column === "password"}
            errorText={updateRequestError?.error}
        />
        <Box sx={{marginTop: "10px"}}>
            <PasswordInputField 
                label="New Password" 
                value={newPassword} 
                onChange={setNewPassword}
                error={updateRequestError != null && updateRequestError?.column === "newPassword"}
                errorText={updateRequestError?.error}
            />
        </Box>
        {updateRequestError && updateRequestError.column === "" ? <FormHelperText error>{updateRequestError.error}</FormHelperText> : undefined}
        <Box sx={{marginTop: "10px"}} flexDirection="column" display="flex">
            <Button variant="contained" onClick={handleUpdateBtnClick}>Update</Button>
        </Box>
    </Box>
}
export default EditPasswordForm