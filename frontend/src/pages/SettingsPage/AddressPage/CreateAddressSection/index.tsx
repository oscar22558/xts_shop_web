import { Box, Button, Divider } from "@mui/material"
import AddIcon from '@mui/icons-material/Add';
import { useState } from "react";
import CreateAddressForm from "../../../../components/CreateAddressForm";

const CreateAddressSection = ()=>{
    const [isCreateAddressFormShown, setIsCreateAdressFormShown] = useState(false)

    const handleControlFormVisibilityBtnClick = ()=>setIsCreateAdressFormShown(!isCreateAddressFormShown)
    const handleUserFinishedAddAddress = ()=>setIsCreateAdressFormShown(false)
    return <Box>
        <Button variant="outlined" sx={{width: "100%"}} onClick={handleControlFormVisibilityBtnClick}>
        {
            isCreateAddressFormShown
            ? <span>Cancel</span>
            : <AddIcon />    
        }
        </Button>
        {
            isCreateAddressFormShown
            ? <>
                <Divider sx={{marginY: "10px"}}/>
                <CreateAddressForm onUserFinishedAddAddress={handleUserFinishedAddAddress}/>
            </>
            : undefined
        }
    </Box>
}
export default CreateAddressSection