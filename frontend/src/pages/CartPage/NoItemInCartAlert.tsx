import { Link, Box, Button } from "@mui/material"

const NotItemInCartAlert = ()=>{
    return (
        <Box sx={{height: "300px", display: "flex", flexDirection: "column", justifyContent: "center", alignItems: "center", border: "1px solid #dedede", borderRadius: "20px"}}>
            <Box sx={{paddingY: "10px"}}>Your cart is empty.</Box>
            <Button variant="outlined" sx={{padding: "0px"}}>
                <Link href="/" underline="none" sx={{paddingY: "5px", paddingX: "15px"}}>
                    Go Shopping
                </Link>
            </Button>
        </Box>
    )
}
export default NotItemInCartAlert