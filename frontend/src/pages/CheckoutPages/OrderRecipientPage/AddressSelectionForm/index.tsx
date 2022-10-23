import { FormControl, FormControlLabel, Radio, Divider, Box } from "@mui/material";
import { useState } from "react";
import { useAppSelector } from "../../../../features/Hooks";
import useCacheShippingAddress from "../../../../features/order/hooks/useCacheShippingAddress";
import UserSelector from "../../../../features/user/UserSelector";

const AddressSelectionForm = ()=>{
    const {addresses} = useAppSelector(UserSelector).getUserResponse.data
    const [selectedAddressId, setSelectedAddressId] = useState(-1)
    const cacheShippingAddress = useCacheShippingAddress()

    const handleChange = ({target}: React.ChangeEvent<HTMLInputElement>) => {
        const addressId = Number.parseInt(target.value)
        cacheShippingAddress(addressId)
        setSelectedAddressId(addressId)
    };
    return (
        <FormControl sx={{width: "100%", marginBottom: "10px"}}>
            {addresses.map(({id, row1, row2, district, area}, index)=>(<Box key={index}>
                <FormControlLabel 
                    control={<Radio 
                        value={id.toString()}
                        checked={selectedAddressId == id}
                        onChange={handleChange}
                    />}
                    label={<>
                        <div>{row1}</div>
                        <div>{row2 || "-"}</div>
                        <div>{district}</div>
                        <div>{area}</div>
                    </>}
                    key={index} 
                    sx={{paddingX: "20px", paddingY: "15px", marginBottom: "10px"}}
                />
                <Divider />
            </Box>))}
        </FormControl>
    )
}
export default AddressSelectionForm