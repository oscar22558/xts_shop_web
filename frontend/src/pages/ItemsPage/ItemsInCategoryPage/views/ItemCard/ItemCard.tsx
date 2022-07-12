import {Card, CardHeader, CardMedia, CardContent, IconButton, Box} from "@mui/material";
import useViewModel from "./useViewModel";
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';

const ItemCard = ({index}: {index: number})=>{
    const viewModel = useViewModel(index)
    return (
        <Card sx={{width: 240, marginBottom: 4, marginRight: 4}}>
            <CardMedia
                sx={{width: 240, height: 240, objectFit: "contain"}}
                component="img"
                image={viewModel.imgUrl}
                alt={viewModel.name}
            />
            <Box sx={{display: "flex", flexDirection: "row", justifyContent: "space-between", alignItems: "center"}}>
                <CardHeader sx={{"& .MuiCardHeader-title": {fontSize: "25px", color: "#ff0000"}}} title={"HKD $"+viewModel.price.value}/>
                <IconButton 
                    color="primary" 
                    aria-label="add to shopping cart" 
                    onClick={viewModel.handleAddItemToCartBtnClick}
                >
                    <AddShoppingCartIcon />
                </IconButton>
            </Box>
            <CardContent sx={{paddingX: "16px", paddingY: "0px"}}>
                {viewModel.name}
            </CardContent>
        </Card>
    )
}
export default ItemCard