import { Box } from "@mui/material"

type Props = {
    label: string
    content: string
}

const OrderDetailColumn = ({label, content}: Props)=>{
    return <Box sx={{flex: 1}}>
        <div>{label}</div>
        <div>{content}</div>
    </Box>
}
export default OrderDetailColumn