import { Box } from "@mui/material"

const containerStyle ={
    height: "40px", 
    widht: "100%", 
    background: "#1976d2", 
    marginTop: "10px", 
    paddingX: "50px", 
    display: "flex", 
    alignItems: "center",
    color: "#fff"
}
const MiniFooter = ()=>{
    return <Box sx={containerStyle}>
       <Box>XTS shop</Box>
    </Box>
}
export default MiniFooter