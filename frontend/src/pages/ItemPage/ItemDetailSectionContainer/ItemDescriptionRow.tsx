import { Box, Divider } from "@mui/material"

type Props = {
    children: JSX.Element | JSX.Element[] | undefined | null
}

const ItemDescriptionRow: React.FC<Props> = ({children})=>{
    return (
        <Box >
            <Box sx={{display: "flex", flexDirection: "row", justifyContent: "flex-start", alignItems: "center", margin: "20px", height: "40px"}}>
                {children}
            </Box>
            <Divider />
        </Box>
    ) 

}
export default ItemDescriptionRow