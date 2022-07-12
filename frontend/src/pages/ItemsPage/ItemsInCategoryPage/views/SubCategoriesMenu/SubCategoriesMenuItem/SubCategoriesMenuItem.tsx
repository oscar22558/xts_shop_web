import {Box, IconButton} from "@mui/material";
import Item from '@mui/material/ListItem';
import Button from '../views/LowerCaseButton';
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
            <Box sx={{
                    width: "100%",
                    display: "flex", 
                    flexDirection: "row",
                    justifyContent: "space-between", 
                    alignItems: "center"
                }}
            >
                <Button sx={{flexGrow: 1, textAlign: "start", textTransform: "none"}} onClick={onClick}>
                    <Text>{label}</Text>
                </Button>
                <IconButton 
                    onClick={handleExpandBtnClick}
                    sx={{":hover": {background: "rgba(0,0,0,0)"}}}
                >{ 
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