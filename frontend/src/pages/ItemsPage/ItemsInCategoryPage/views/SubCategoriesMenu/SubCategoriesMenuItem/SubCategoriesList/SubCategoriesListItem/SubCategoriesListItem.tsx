import { Box } from "@mui/material"
import Button from "../../../views/LowerCaseButton"
import Text from "@mui/material/ListItemText"
interface Props{
    label: string
    onClick?: ()=>void
}
const SubCategoriesListItem = ({
    label,
    onClick
}: Props)=>{
    return <Button sx={{width: "100%"}} onClick={onClick}>
        <Text sx={{textAlign: "start", paddingLeft: "20px"}}>{label}</Text>
    </Button>
}
export default SubCategoriesListItem