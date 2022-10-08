import { Box } from "@mui/material"
import ItemCard from "./ItemCard/ItemCard"
import useViewModel from "./useViewModel"

const ItemList = ()=>{
    
    const data = useViewModel()
    return (
        <Box sx={{flexDirection: "row", display: "flex"}}>
            {data.map((vm, index)=>(<ItemCard key={index} index={index} />))}
        </Box>
    )
}
export default ItemList