import {Card, CardHeader, CardMedia} from "@mui/material";
import useViewModel from "./useViewModel";

const ItemCard = ({index}: {index: number})=>{
    const data = useViewModel(index)
    return (
        <Card sx={{width: 240, marginBottom: 4, marginRight: 4}}>
            <CardMedia
                sx={{width: 240, height: 240, objectFit: "contain"}}
                component="img"
                image={data.imgUrl}
                alt={data.name}
            />
            <CardHeader sx={{"& .MuiCardHeader-title": {fontSize: "30px", color: "#ff0000"}}} title={"HKD $"+data.price.value}/>
            <div >{data.name}</div>
        </Card>
    )
}
export default ItemCard