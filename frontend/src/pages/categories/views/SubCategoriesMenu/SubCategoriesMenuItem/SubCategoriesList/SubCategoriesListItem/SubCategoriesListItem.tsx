import { Box, Button } from "@mui/material"
import Text from "@mui/material/ListItemText"
interface Props{
    label: string
    onClick?: ()=>void
}
const SubCategoriesListItem = ({
    label,
    onClick
}: Props)=>{
    return <Button onClick={onClick}><Text>{label}</Text></Button>
}
export default SubCategoriesListItem