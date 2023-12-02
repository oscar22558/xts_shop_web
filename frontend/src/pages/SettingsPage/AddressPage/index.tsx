import { Box, Button, IconButton, Paper, Typography } from "@mui/material"
import DeleteIcon from '@mui/icons-material/Delete';

import { useAppSelector } from "../../../features/Hooks"
import useSendDeleteAddressRequest from "../../../features/user-addresses/hooks/useSendDeleteAddressRequest"
import UserSelector from "../../../features/user/UserSelector"
import CreateAddressSection from "./CreateAddressSection"

const AddressPage = ()=>{
    const {addresses} = useAppSelector(UserSelector).getUserResponse.data

    const sendDeleteAddressReuest = useSendDeleteAddressRequest()

    const handleDeleteBtnClick = (addressId: number)=>()=>{
        sendDeleteAddressReuest(addressId)        
    }

    return <Box sx={{paddingBottom: "10px"}}>
        <Typography variant="h4">Addresses</Typography>
        {addresses.map((address, index)=>(
                <Paper key={index} sx={{display: "flex", flexDirection: "row",paddingX: "20px", paddingY: "15px", marginBottom: "10px"}}>
                    <Box sx={{flex: 1}}>
                        <div style={{paddingBottom: "5px"}}>{address.row1}</div>
                        <div style={{paddingBottom: "5px"}}>{address.row2 || "-"}</div>
                        <div style={{paddingBottom: "5px"}}>{address.district}</div>
                        <div style={{paddingBottom: "5px"}}>{address.area}</div>
                    </Box>
                    <Box sx={{display: "flex", alignItems: "center"}}>
                    <IconButton aria-label="delete" onClick={handleDeleteBtnClick(address.id)}>
                        <DeleteIcon />
                    </IconButton>
                    </Box>
                </Paper>
            
            ))}
        <CreateAddressSection />
    </Box>
}
export default AddressPage