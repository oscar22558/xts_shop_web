
import { Box, Button, Grid } from "@mui/material"
import { useState } from "react"
import QuantityInput from "../../../../components/QuantityInput"
import useCart from "../../../../data-sources/cart/useCart"
import Item from "../../../../features/items/models/Item"
import { toUpperCaseAt } from "../../../../utli"
import ItemDescriptionRow from "../ItemDescriptionRow"
import {host} from "../../../../features/ApiConfig"

const ItemDetailSection: React.FC<Item> = ({
    id, name, price, imgUrl, brand, manufacturer
})=>{
    const [quantity, setQuantity] = useState(1)
    const {addCartItem} = useCart()
    const itemName = toUpperCaseAt(name,  0)
    const viewModels = [
        {label: "Price", content: price.toString()},
        {label: "Brand", content: brand ?? ""},
        {label: "Manufacturer", content: manufacturer ?? ""},
    ]

    const handleAddBtnClick = ()=>{
        setQuantity(quantity + 1)
    }

    const handleRemoveBtnClick = ()=>{
        if(quantity <= 1) return
        setQuantity(quantity - 1)
    }

    const handleAddItemToCart = ()=>{
        addCartItem(id, quantity)
    }

    return (
        <Box>
            <h2>{itemName}</h2>
            <Grid container>
                <Grid item>
                    <img src={`${host}/${imgUrl}`} alt={itemName} style={{width: "400px", height: "400px"}}/>
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
                            <QuantityInput 
                                quantity={quantity} 
                                onAddBtnClick={handleAddBtnClick} 
                                onRemoveBtnClick={handleRemoveBtnClick}
                            />
                        </Box>
                    </ItemDescriptionRow>
                    <Box sx={{display: "flex", justifyContent: "flex-end", alignItems: "center", padding: "20px"}}>
                        <Button variant="contained" onClick={handleAddItemToCart}>Add to Cart</Button>
                    </Box>
                </Grid>
            </Grid>
        </Box>
    )
}
export default ItemDetailSection