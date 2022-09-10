import { FormControl, FormControlLabel, Radio, Divider, Box } from "@mui/material";
import { useState } from "react";
import { useAppSelector } from "../../../features/Hooks";
import useCacheShippingAddress from "../../../features/order/hooks/useCacheShippingAddress";
import UserSelector from "../../../features/user/UserSelector";

const AddressSelectionForm = ()=>{
    const {addresses} = useAppSelector(UserSelector).getUserResponse.data
    const [selectedIndex, setSelectedIndex] = useState<string|null>(null);
    const cacheShippingAddress = useCacheShippingAddress()

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSelectedIndex(event.target.value);
        const selectedIndexNumber = Number.parseInt(event.target.value)
        const addressId = addresses[selectedIndexNumber]?.id ?? -1
        cacheShippingAddress(addressId)
    };
    return (
        <FormControl sx={{width: "100%", marginBottom: "10px"}}>
            {addresses.map((address, index)=>(<Box key={index}>
                <FormControlLabel 
                    control={<Radio 
                        value={index.toString()}
                        checked={selectedIndex == index.toString()}
                        onChange={handleChange}
                    />}
                    label={<>
                        <div>{address.row1}</div>
                        <div>{address.row2 || "-"}</div>
                        <div>{address.district}</div>
                        <div>{address.area}</div>
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