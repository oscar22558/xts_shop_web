import { Box, Grid } from "@mui/material"
type Props = {
    imgUrl?: string
    name: string
    quantity: string
}

const OrderItem = ({name, quantity, imgUrl}: Props)=>{
    return <Box sx={{height: "100px", marginBottom: "20px"}}>
        <Grid container direction="row" sx={{height: "100%"}}>
            <Grid item xs={2}>
                <img height="100px" width="100px" src={imgUrl}/>
            </Grid>
            <Grid item xs={10}>
                <>
                    <div>{name}</div>
                    <div>x {quantity}</div>
                </>
            </Grid>
        </Grid>
    </Box>
}
export default OrderItem