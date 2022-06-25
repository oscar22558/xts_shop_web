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
                    justifyContent: "space-between", 
                    alignItems: "center"
                }}
            >
                <Button 
                    sx={{flex: 1, textAlign: "start", textTransform: "none"}}
                >
                    <Text onClick={onClick}>{label}</Text>
                    <IconButton 
                        onClick={handleExpandBtnClick}
                        sx={{":hover": {background: "rgba(0,0,0,0)"}}}
                    >{ 
                        isExpanded ? <ExpandLessIcon /> : <ExpandMoreIcon />
                    }</IconButton>
                </Button>
            </Box>
            { isExpanded
                ? (<SubCategoriesList index={index}/>)
                : (undefined)
            }
        </Item>
    )
}
export default SubCategoriesMenuItem