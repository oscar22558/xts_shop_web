import { Grid, Link } from "@mui/material"
import useCart from "../../../data-sources/cart/useCart"
import CheckBoxList from "./CheckBoxList"
import QuantityInput from "./CartItemQuantityInput"
import AppRouteList from "../../../routes/AppRouteList"
import { useNavigate } from "react-router-dom"
import {host} from "../../../features/ApiConfig"

const CartItemList = ()=>{

    const navigate = useNavigate()
    const {itemCountsInCart, itemsInCart} = useCart()
    const itemIds = itemsInCart.map((item)=>item.id)
    const handleItemClick = (itemId: number)=>()=>{
        const {route, params} = AppRouteList.item
        navigate(route.replace(params[0], itemId.toString()))
    }
    return <>
        <CheckBoxList data={{ids: itemIds}} rowContent={(id, isChecked, index)=>{
            const item = itemsInCart[index]
            return (<Grid container>
                <Grid item xs={10}>
                    <Grid container>
                        <Grid item xs={2} sx={{display: "flex"}}>
                            <img 
                                src={`${host}/${item.imgUrl}`} 
                                alt={item.name} 
                                onClick={handleItemClick(item.id)} 
                                style={{cursor: "pointer", width: "60px", height: "60px"}}
                            />
                        </Grid>
                        <Grid item xs={10}>
                            <div>{id}: {itemCountsInCart ? itemCountsInCart[id]: 0}</div>
                            <Link
                                sx={{cursor: "pointer"}}
                                onClick={handleItemClick(item.id)}
                            >{item.name}</Link>
                            <div>HKD ${item.price}</div>
                        </Grid>
                    </Grid>
                </Grid>
                <Grid item xs={2}>
                    <QuantityInput itemId={item.id}/>
                </Grid>
            </Grid>)
        }}/>
    </>
}
export default CartItemList