import {Box, IconButton} from "@mui/material";
import Item from '@mui/material/ListItem';
import Button from '../views/LowerCaseButton';
import Text from '@mui/material/ListItemText';
import ExpandLessIcon from '@mui/icons-material/ExpandLess';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { useState } from "react";
import SubCategoriesList from "./SubCategoriesList/SubCategoriesList";
import { useAppSelector } from "../../../../../../features/Hooks";
import CategoriesSelector from "../../../../../../features/categories/CategoriesSelector";

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

    const { data: category } = useAppSelector(CategoriesSelector).getCategoryResponse
    const subCategoriesCount = category?.subCategories[index].subCategories.length
    const [isExpanded, setIsExpanded] = useState(false)
    const handleExpandBtnClick = ()=>{
        setIsExpanded(!isExpanded)
    }

    return (
        <Item sx={{width: "100%", display: "flex", flexDirection: "column"}}>
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
                {
                    subCategoriesCount != null && subCategoriesCount > 0 ? (
                        <IconButton 
                            onClick={handleExpandBtnClick}
                            sx={{":hover": {background: "rgba(0,0,0,0)"}}}
                        >{ 
                            isExpanded ? <ExpandLessIcon /> : <ExpandMoreIcon />
                        }</IconButton>
                    )
                    : <></>
                }
            </Box>
            { isExpanded
                ? (<SubCategoriesList index={index}/>)
                : (undefined)
            }
        </Item>
    )
}
export default SubCategoriesMenuItem