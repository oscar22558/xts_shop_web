import {Card, CardHeader, CardMedia, CardContent, IconButton, Box} from "@mui/material";
import useViewModel from "./useViewModel";
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import { useNavigate } from "react-router-dom";
import AppRouteList from "../../../../../../routes/AppRouteList";

const ItemCard = ({index}: {index: number})=>{
    const navigate = useNavigate()
    const viewModel = useViewModel(index)
    const {route, params} = AppRouteList.item
    const handleItemImgClick = ()=>{
        const navigationRoute = route.replace(`${params[0]}`, viewModel.id.toString())
        navigate(navigationRoute)
    }
    return (
        <Card sx={{minWidth: 240, marginBottom: 4, marginRight: 4}}>
            <CardMedia
                sx={{width: 240, height: 240, objectFit: "contain", cursor: "pointer"}}
                component="img"
                image={viewModel.imgUrl}
                alt={viewModel.name}
                onClick={handleItemImgClick}
            />
            <Box sx={{display: "flex", flexDirection: "row", justifyContent: "space-between", alignItems: "center"}}>
                <CardHeader sx={{"& .MuiCardHeader-title": {fontSize: "25px", color: "#ff0000"}}} title={"HKD $"+viewModel.price}/>
                <IconButton 
                    color="primary" 
                    aria-label="add to shopping cart" 
                    onClick={viewModel.handleAddItemToCartBtnClick}
                >
                    <AddShoppingCartIcon />
                </IconButton>
            </Box>
            <CardContent sx={{paddingX: "16px", paddingY: "0px"}}>
                <div>{viewModel.brand}</div>
                <div>{viewModel.name}</div>
            </CardContent>
        </Card>
    )
}
export default ItemCard