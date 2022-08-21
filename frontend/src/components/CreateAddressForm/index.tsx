import React, { useEffect, useState } from "react"
import { Button, FormHelperText, Grid } from "@mui/material"
import useSentAddAddressRequest from "../../redux/user-addresses/hooks/useSendAddAddressRequest"
import AddAddressForm from "../../redux/user-addresses/models/AddAddressForm"
import StyledTextFieldWithFormControl from "./StyledTextFieldWithFormControl"
import SelectWithFormControl from "./components/StyledSelectWithFormControl"
import District from "./models/District"
import Area from "./models/Area"
import { useAppSelector } from "../../redux/Hooks"
import UserAddressesSelector from "../../redux/user-addresses/UserAddressesSelector"
import useClearAddAddressRequestState from "../../redux/user-addresses/hooks/useClearAddAddressRequestState"

const initialFormState = {
    country: "China",
    city: "Hong Kong",
    area: "",
    district: "",
    row1: "",
    row2: ""
}
const initialFormColumnError = {
    country: "",
    city: "",
    area: "",
    district: "",
    row1: "",
    row2: ""
}

type Props = {
    onUserFinishedAddAddress?: ()=>void
}

const CreateAddressForm = ({
    onUserFinishedAddAddress
}: Props)=>{
    const [isWaitingUserSendRequest, setIsWaitingUserSendRequest] = useState(true)
    const [addAddressForm, setAddAddressForm] = useState<AddAddressForm>(initialFormState)
    const [formColumnError, setFormColumnError] = useState(initialFormColumnError)
    const {error: addRequestError, loading: addRequestLoading} = useAppSelector(UserAddressesSelector).addAddressResponse
    const sendAddAddressRequest = useSentAddAddressRequest()
    const clearAddAddressRequestState = useClearAddAddressRequestState()

    const showFormValidationError = ()=>{
        const {row1, area, district} = addAddressForm
        const addressRow1Error = !row1 ? "Missing address row 1" : "" 
        const districtError = !district ? "District is not selected" : ""
        const areaError = !area ? "Area is not selected" : ""
        setFormColumnError({
            ...formColumnError, 
            row1: addressRow1Error,
            district: districtError,
            area: areaError
        })
    }

    const validateFormColumns = ()=>{
        console.log("validate form columnes")
        const {row1, area, district} = addAddressForm
        return row1 && area && district
    }

    const handleConfirmBtnClick = ()=>{
        if(!validateFormColumns()){
            showFormValidationError()
            return
        }
        sendAddAddressRequest(addAddressForm)
        setIsWaitingUserSendRequest(false)
    }

    const handleAddressRow1InputChange = (event: React.ChangeEvent<HTMLInputElement>)=>{
       setAddAddressForm({ ...addAddressForm, row1: event.target.value})
    }

    const handleAddressRow2InputChange = (event: React.ChangeEvent<HTMLInputElement>)=>{
       setAddAddressForm({ ...addAddressForm, row2: event.target.value})
    }

    const handleAreaInputChange = (value: keyof typeof Area)=>{
        setAddAddressForm({ ...addAddressForm, area: Area[value]})
    }

    const handleDistrictInputChange = (value: keyof typeof District)=>{
        setAddAddressForm({ ...addAddressForm, district: District[value]})
    }

    useEffect(()=>{
        if(isWaitingUserSendRequest || addRequestLoading){
            return
        }
        if(addRequestError){
            setIsWaitingUserSendRequest(true)
            return
        }
        onUserFinishedAddAddress && onUserFinishedAddAddress()
        return ()=>{
            clearAddAddressRequestState()
        }
    }, [isWaitingUserSendRequest, addRequestError, addRequestLoading, clearAddAddressRequestState])

    return <Grid container direction="column">
        <Grid item sx={{display: "flex"}}>
            <Grid container spacing="10px" direction="column">
                <Grid item>
                    <StyledTextFieldWithFormControl
                        title="Street address row1*" 
                        label="Street address row1*" 
                        onChange={handleAddressRow1InputChange} 
                        error={formColumnError.row1 != ""}
                        errorText={formColumnError.row1}
                    />
                </Grid>
                <Grid item>
                    <StyledTextFieldWithFormControl 
                        title="Street address row2"
                        label="Street address row2" 
                        onChange={handleAddressRow2InputChange}
                    />
                </Grid>
                <Grid item>
                    <SelectWithFormControl 
                        id="District*" 
                        dataSet={District} 
                        error={formColumnError.district != ""}
                        errorText={formColumnError.district}
                        onChange={handleDistrictInputChange}
                    />
                </Grid>
                <Grid item>
                    <SelectWithFormControl 
                        id="Area*" 
                        dataSet={Area} 
                        error={formColumnError.area != ""}
                        errorText={formColumnError.area}
                        onChange={handleAreaInputChange}
                    />
                    {addRequestError ? <FormHelperText error>{addRequestError}</FormHelperText> : undefined}
                </Grid>
            </Grid>
        </Grid>
        <Grid item sx={{paddingY: "10px", display: "flex"}}>
            <Button sx={{flex: 1}} variant="contained" onClick={handleConfirmBtnClick}>Confirm</Button>
        </Grid>
    </Grid>
}
export default CreateAddressForm