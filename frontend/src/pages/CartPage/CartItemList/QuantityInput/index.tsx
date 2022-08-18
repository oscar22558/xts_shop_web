import { Box, IconButton, Input, TextField } from "@mui/material"
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import useCart from "../../../../data-sources/cart/useCart";

type Props = {
    itemId: number
}

const QuantityInput = ({itemId}: Props)=>{
    const {itemCountsInCart, addCartItem, minusItemQunity} = useCart()
    const itemCount = itemCountsInCart[itemId]
    const handleRemoveBtnClick = ()=>{
        addCartItem(itemId)
    }

    const handleAddBtnClick = ()=>{
        minusItemQunity(itemId)
    }

    return <Box sx={{height: "100%", alignItems: "center", justifyContent: "center", flex: 1, display: "flex", flexDirection: "row"}}>
        <IconButton onClick={handleAddBtnClick}>
            <RemoveIcon/>
        </IconButton>
        <Box sx={{width: "70px"}}>{itemCount}</Box>
        <IconButton onClick={handleRemoveBtnClick}>
            <AddIcon />
        </IconButton>
    </Box>
}
export default QuantityInput