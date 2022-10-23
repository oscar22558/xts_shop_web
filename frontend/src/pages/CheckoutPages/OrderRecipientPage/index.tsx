import { Box, Grid, Button, TextField, FormHelperText } from "@mui/material"
import React, { useState } from "react"
import { useNavigate } from "react-router-dom"

import AddressSelectionDialog from "./AddressSelectionDialog"

import { useAppSelector } from "../../../features/Hooks"
import useUpdateOrderCreateForm from "../../../features/order/hooks/useUpdateOrderCreateform"
import OrderSelector from "../../../features/order/OrderSelector"
import UserSelector from "../../../features/user/UserSelector"

const OrderRecipientPage = ()=>{
    const navigate = useNavigate()
    const updateOrderCreateForm = useUpdateOrderCreateForm()
    const cachedOrder = useAppSelector(OrderSelector).cachedOrderCreateForm
    const {addresses} = useAppSelector(UserSelector).getUserResponse.data

    const [isSelectAddressDialogShown, setIsSelectAddressDialogShown] = useState(false)

    const {firstName, lastName, email, phone} = cachedOrder
    const address = addresses.find(address=>address.id === cachedOrder.userAddressId)
    
    const handleNextBtnClick = ()=>{
        navigate("/payment")
    }

    const handleOrderCreateFormChange = ({target}: React.ChangeEvent<HTMLTextAreaElement>)=>{
        updateOrderCreateForm({...cachedOrder, [target.name]: target.value})
    }

    const handleShownSelectAddressDialogClick = ()=>{
        setIsSelectAddressDialogShown(true)
    }

    const handleSelectAddressDialogClose = ()=>{
        setIsSelectAddressDialogShown(false)
    }

    const viewModels = [
        {name: "lastName", hint: "Last Name *",value: lastName, error: "", helperText: ""},
        {name: "firstName", hint: "First Name *", value: firstName, error: "", helperText: ""},
        {name: "email", hint: "Email *", value: email, error: "", helperText: "Provide your email address to us so that we can inform you the order latest state in email."},
        {name: "phone", hint: "Phone(Optional)",value: phone, error: "", helperText: "Provide your phone number so that you can get informed about the shipping state of the order."},
    ]
    const addressTextFieldData = [
        {name: "row1", hint: "Address Row 1", value: address?.row1 ?? ""},
        {name: "row2", hint: "Address Row 2", value: address?.row2 ?? ""},
        {name: "district", hint: "District", value: address?.district ?? ""},
        {name: "area", hint: "Area", value: address?.area ?? ""}
    ]
    const isNextBtnDisable = address == null || firstName === "" || lastName === "" || email === ""
    return <Box display="flex" flexDirection="column" alignItems="center">
        <Box display="flex" justifyContent="flex-start" width="500px">
            <h1>Order Recipient</h1>
        </Box>
        <Grid container spacing="20px" direction="column" maxWidth="500px">
            <Grid item xs>
                {
                    viewModels.map(({name, hint, value, error, helperText}, index)=>(
                        <Box key={index} display="flex" flexDirection="column" marginBottom="15px">
                            <TextField
                                autoComplete={name}
                                name={name}  
                                type="text" 
                                title={hint}
                                label={hint}
                                error={error !== ""}
                                sx={{width: "500px"}}
                                value={value} 
                                onChange={handleOrderCreateFormChange}
                                helperText={helperText}
                            />
                            {error !== "" && <FormHelperText error>{error}</FormHelperText>}
                        </Box>
                    ))
                }

            </Grid>
            <Grid item xs>
                <AddressSelectionDialog isShown={isSelectAddressDialogShown} onClose={handleSelectAddressDialogClose}/>
                <Box marginBottom="30px" display="flex" flexDirection="row" justifyContent="space-between">
                    <Box fontSize="20px" fontWeight="bond" marginRight="20px">Shipping address</Box>
                    <Button variant="outlined" onClick={handleShownSelectAddressDialogClick}>Edit</Button>
                </Box>
                <Box sx={{display: "flex", flexDirection: "column", alignItems: "flex-start", justifyContent: "center"}}>
                    {addressTextFieldData.map(({name, hint, value}, index)=>(
                        <TextField
                            key={index}
                            name={name}  
                            type="text" 
                            title={hint}
                            label={hint}
                            sx={{width: "500px", marginBottom: "15px"}}
                            InputProps={{
                                readOnly: true,
                            }}
                            value={value} 
                        />
                    ))}
                </Box>
            </Grid>
        </Grid>
        <Box sx={{display: "flex", justifyContent: "center", marginTop: "20px"}}>
            <Button disabled={isNextBtnDisable} variant="contained" onClick={handleNextBtnClick}>Next</Button>
        </Box>
    </Box>

}
export default OrderRecipientPage