import { FormControl, FormControlLabel, Radio, Divider, Box } from "@mui/material";
import { useAppSelector } from "../../../features/Hooks";
import useCacheShippingAddress from "../../../features/order/hooks/useCacheShippingAddress";
import OrderSelector from "../../../features/order/OrderSelector";
import UserSelector from "../../../features/user/UserSelector";

const AddressSelectionForm = ()=>{
    const {addresses} = useAppSelector(UserSelector).getUserResponse.data
    const selectedShippingAddressId = useAppSelector(OrderSelector).cachedOrderCreateForm.userAddressId
    const cacheShippingAddress = useCacheShippingAddress()

    const handleChange = ({target}: React.ChangeEvent<HTMLInputElement>) => {
        const addressId = Number.parseInt(target.value)
        cacheShippingAddress(addressId)
    };
    return (
        <FormControl sx={{width: "100%", marginBottom: "10px"}}>
            {addresses.map(({id, row1, row2, district, area}, index)=>(<Box key={index}>
                <FormControlLabel 
                    control={<Radio 
                        value={id.toString()}
                        checked={selectedShippingAddressId == id}
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