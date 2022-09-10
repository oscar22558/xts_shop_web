import { Box, Button, Divider, Grid } from "@mui/material"
import { useState } from "react"
import QuantityInput from "../../../components/QuantityInput"
import { useAppSelector } from "../../../features/Hooks"
import ItemDetailSelector from "../../../features/item-detail/ItemDetailSelector"
import ItemDescriptionRow from "./ItemDescriptionRow"

const ItemDetailSection = ()=>{
    const [quantity, setQuantity] = useState(1)
    const {data: item} = useAppSelector(ItemDetailSelector).getItemDetailResponse
    const itemPrice = item?.price.value ?? 0
    const itemImgUrl = item?.imgUrl ?? ""
    const itemName = item?.name ?? ""
    const viewModels = [
        {label: "Price", content: itemPrice.toString()},
        {label: "Brand", content: item?.brand ?? ""},
        {label: "Manufacturer", content: item?.manufacturer ?? ""},
    ]

    const handleAddBtnClick = ()=>{
        setQuantity(quantity + 1)
    }

    const handleRemoveBtnClick = ()=>{
        if(quantity <= 1) return
        setQuantity(quantity - 1)
    }
    return (
        <Box>
            <Grid container>
                <Grid item>
                    <img src={itemImgUrl} alt={itemName} style={{width: "300px", height: "400px"}}/>
                </Grid>
                <Grid item xs sx={{marginLeft: "40px"}}>
                    {viewModels.map(({label, content}, index)=>(
                        <ItemDescriptionRow key={index}>
                                <Box sx={{flex: 25}}>{label}</Box>
                                <Box sx={{flex: 75}}>{content}</Box>
                        </ItemDescriptionRow>
                        )
                    )}
                    <ItemDescriptionRow>
                        <Box sx={{flex: 25}}>Quantity</Box>
                        <Box sx={{flex: 75, display: "flex", justifyContent: "flex-start"}}>
                            <QuantityInput quantity={quantity} onAddBtnClick={handleAddBtnClick} onRemoveBtnClick={handleRemoveBtnClick}/>
                        </Box>
                    </ItemDescriptionRow>
                    <Box sx={{display: "flex", justifyContent: "flex-end", alignItems: "center", padding: "20px"}}>
                        <Button variant="contained">Add to Cart</Button>
                    </Box>
                </Grid>
            </Grid>
            <Box sx={{paddingY: "20px"}}>Item Description</Box>
            <Divider />
            <Box sx={{paddingY: "20px"}}>Other items</Box>
            <Divider />
        </Box>

    )
}
export default ItemDetailSection