import { Box, IconButton } from "@mui/material"
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';

type Props = {
    quantity: number
    onRemoveBtnClick?: ()=>void
    onAddBtnClick?: ()=>void
}

const QuantityInput: React.FC<Props> = ({quantity: itemCount, onAddBtnClick, onRemoveBtnClick})=>{
    return <Box sx={{alignItems: "center", justifyContent: "center", display: "flex", flexDirection: "row"}}>
        <IconButton onClick={onRemoveBtnClick}>
            <RemoveIcon/>
        </IconButton>
        <Box sx={{width: "70px", display: "flex", justifyContent: "center"}}><Box>{itemCount}</Box></Box>
        <IconButton onClick={onAddBtnClick}>
            <AddIcon />
        </IconButton>
    </Box>
}
export default QuantityInput