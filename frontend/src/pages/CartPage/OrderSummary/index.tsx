import { Box, Button, Divider, Grid } from "@mui/material"

const OrderSummary = ()=>{
    const subtotal = 1
    const shipping = 1
    const total = 2
    return <Box sx={{border: "1px solid", borderRadius: "10px", padding: "20px", display: "flex", flexDirection: "column",justifyContent: "stretch"}}>
        <Grid container>
            <Grid item xs={9}>
                <div>Subtotal:</div>
                <div>Shipping:</div>
                <div>Total:</div>
            </Grid>
            <Grid item xs={3} >
                <Box sx={{diplay: "flex", alignItems: "flex-end", flexDirection: "column", textAlign: "right"}}>
                    <div>{subtotal}</div>
                    <div>{shipping}</div>
                    <div>{total}</div>
                </Box>
            </Grid>
        </Grid>
        <Divider />
        <Button variant="contained" sx={{marginTop: "10px"}}>Check out</Button>
    </Box>
}
export default OrderSummary