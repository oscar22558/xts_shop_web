import { Box, Grid, Button, TextField, FormHelperText } from "@mui/material"
import React, { useState } from "react"
import { useNavigate } from "react-router-dom"

import AddressSelectionDialog from "./AddressSelectionDialog"

import { useAppDispatch, useAppSelector } from "../../../features/Hooks"
import useUpdateOrderCreateForm from "../../../features/order/hooks/useUpdateOrderCreateform"
import OrderSelector from "../../../features/order/OrderSelector"

type Props = {
    onPreviousClick?: ()=>void
    onNextClick?: ()=>void
}
const OrderRecipientPage: React.FC<Props> = ({onPreviousClick, onNextClick})=>{
    const navigate = useNavigate()
    const updateOrderCreateForm = useUpdateOrderCreateForm()
    const cachedOrder = useAppSelector(OrderSelector).cachedOrderCreateForm

    const [isSelectAddressDialogShown, setIsSelectAddressDialogShown] = useState(false)

    const {firstName, lastName, email, phone, area, district, row1, row2} = cachedOrder
    
    const handleNextBtnClick = ()=>{
        onNextClick?.()
    }

    const handlePreviousClick = ()=>{
        onPreviousClick?.()
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

    const handleShippingAddressColumnsChange = ({target}: React.ChangeEvent<HTMLTextAreaElement>)=>{
        updateOrderCreateForm({...cachedOrder, [target.name]: target.value})
    }

    const viewModels = [
        {name: "lastName", hint: "Last Name *",value: lastName, error: "", helperText: ""},
        {name: "firstName", hint: "First Name *", value: firstName, error: "", helperText: ""},
        {name: "email", hint: "Email *", value: email, error: "", helperText: "Provide your email address to us so that we can inform you the order latest state in email."},
        {name: "phone", hint: "Phone(Optional)",value: phone, error: "", helperText: "Provide your phone number so that you can get informed about the shipping state of the order."},
    ]
    const addressTextFieldData = [
        {name: "row1", hint: "Address Row 1", value: row1 ?? ""},
        {name: "row2", hint: "Address Row 2", value: row2 ?? ""},
        {name: "district", hint: "District", value: district ?? ""},
        {name: "area", hint: "Area", value: area ?? ""}
    ]
    const isNextBtnDisable = row1 === "" 
        || district === ""
        || area === ""
        || firstName === "" 
        || lastName === "" 
        || email === ""

    return <Box display="flex" flexDirection="column" alignItems="center" maxWidth="500px">
            <Box display="flex" justifyContent="flex-start" width="100%" paddingBottom="20px">
                <Box fontSize="20px" fontWeight="bond" marginRight="20px">Order Recipient</Box>
            </Box>
            <Grid container direction="column" width="100%">
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
                    </Box>
                    <Button variant="outlined" onClick={handleShownSelectAddressDialogClick} sx={{textTransform: "none"}}>Choose from Saved Addresses</Button>
                    <Box sx={{display: "flex", flexDirection: "column", alignItems: "flex-start", justifyContent: "center"}}>
                        {addressTextFieldData.map(({name, hint, value}, index)=>(
                            <TextField
                                key={index}
                                name={name}  
                                type="text" 
                                title={hint}
                                label={hint}
                                sx={{width: "500px", marginBottom: "15px"}}
                                value={value} 
                                onChange={handleShippingAddressColumnsChange}
                            />
                        ))}
                    </Box>
                </Grid>
            </Grid>
            <Box sx={{display: "flex", justifyContent: "space-around", marginTop: "20px", width: "100%"}}>
                <Button sx={{width: "200px"}} variant="text" onClick={handlePreviousClick}>Previous</Button>
                <Button sx={{width: "200px"}} disabled={isNextBtnDisable} variant="contained" onClick={handleNextBtnClick}>Next</Button>
            </Box>
    </Box>
}
export default OrderRecipientPage