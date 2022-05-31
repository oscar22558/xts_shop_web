import {Box, IconButton} from "@mui/material";
import Item from '@mui/material/ListItem';
import Button from '@mui/material/Button';
import Text from '@mui/material/ListItemText';
import ExpandLessIcon from '@mui/icons-material/ExpandLess';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { useState } from "react";
import SubCategoriesList from "./SubCategoriesList/SubCategoriesList";

interface Props {
    index: number
    label: string
    onClick?: ()=>void   
}
const SubCategoriesMenuItem = ({
    index,
    label,
    onClick
}: Props)=>{
    const [isExpanded, setIsExpanded] = useState(false)
    const handleExpandBtnClick = ()=>{
        setIsExpanded(!isExpanded)
    }
    return (
        <Item sx={{display: "flex", flexDirection: "column"}}>
            <Box sx={{width: "100%", display: "flex", justifyContent: "space-between"}}>
                <Button sx={{flex: 1, textAlign: "start"}} onClick={onClick}><Text>{label}</Text></Button>
                <IconButton onClick={handleExpandBtnClick}>{ 
                    isExpanded ? <ExpandLessIcon /> : <ExpandMoreIcon />
                }</IconButton>
            </Box>
            { isExpanded
                ? (<SubCategoriesList index={index}/>)
                : (undefined)
            }
        </Item>
    )
}
export default SubCategoriesMenuItem