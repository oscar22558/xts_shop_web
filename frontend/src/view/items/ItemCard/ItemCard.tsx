import React from "react"
import {Card, CardContent, CardHeader, CardMedia} from "@mui/material";
import useViewModel from "./vm/useViewModel";
import {Label} from "@mui/icons-material";

const ItemCard = ({index}: {index: number})=>{
    const data = useViewModel(index)
    return (
        <Card>
            <CardMedia
                component="img"
                height="140"
                image={data.imgUrl}
                alt={data.name}
            />
            <CardHeader title={data.name}/>
            <div>{data.price}</div>
        </Card>
    )
}
export default ItemCard