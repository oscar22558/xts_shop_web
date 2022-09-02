import { Box, Button, Paper, Typography } from "@mui/material"
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
        {addresses.map((address, index)=>
            <Paper key={index} sx={{paddingX: "20px", paddingY: "15px", marginBottom: "10px"}}>
                <div>{address.row1}</div>
                <div>{address.row2 || "-"}</div>
                <div>{address.district}</div>
                <div>{address.area}</div>
                <Box sx={{display: "flex", flexDirection: "row", justifyContent: "flex-end"}}>
                    <Button onClick={handleDeleteBtnClick(address.id)}>Delete</Button>
                </Box>
            </Paper>)}
        <CreateAddressSection />
    </Box>
}
export default AddressPage