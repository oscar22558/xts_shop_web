import { Box, Grid, Link } from "@mui/material"
import { useNavigate } from "react-router-dom"
import { host } from "../../features/ApiConfig"
import AppRouteList from "../../routes/AppRouteList"
type Props = {
    itemId: number
    imgUrl?: string
    name: string
    quantity: string
    price: number
}

const OrderItem = ({itemId, name, quantity, imgUrl, price}: Props)=>{
    const navigate = useNavigate()
    const {route, params} = AppRouteList.item
    const handleItemClick = ()=>{
        navigate(route.replace(params[0], itemId.toString()))
    }
    const regex = /^(\/)/i
    return <Box sx={{height: "100px", marginBottom: "20px"}}>
        <Grid container direction="row" sx={{height: "100%"}}>
            <Grid item xs={2}>
                <img height="100px" width="100px" src={`${host}/${imgUrl?.replace(regex, '')}`} alt={name} 
                style={{cursor: "pointer"}}
                onClick={handleItemClick}
                />
            </Grid>
            <Grid item xs={10}>
                <>
                    <Link 
                        sx={{cursor: "pointer"}} 
                        onClick={handleItemClick}
                    >{name}</Link>
                    <div>HKD ${price}</div>
                    <div>x {quantity}</div>
                </>
            </Grid>
        </Grid>
    </Box>
}
export default OrderItem