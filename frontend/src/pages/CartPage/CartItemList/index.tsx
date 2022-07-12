import { Grid } from "@mui/material"
import useCart from "../../../data-sources/cart/useCart"
import CheckBoxList from "./CheckBoxList"
import QuantityInput from "./QuantityInput"

const CartItemList = ()=>{

    const {itemCountsInCart, itemsInCart} = useCart()
    const itemIds = itemsInCart.map((item)=>item.id)
    return <>
        <CheckBoxList data={{ids: itemIds}} rowContent={(id, isChecked, index)=>{
            const item = itemsInCart[index]
            return (<Grid container>
                <Grid item xs={10}>
                    <Grid container>
                        <Grid item xs={2}>
                            <img  src={item.imgUrl}/>
                        </Grid>
                        <Grid item xs={10}>
                            <div>{id}: {itemCountsInCart ? itemCountsInCart[id]: 0}</div>
                            <div>{item.name}</div>
                            <div>HKD ${item.price.value}</div>
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