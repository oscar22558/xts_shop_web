import React from 'react';
import {Paper} from "@mui/material";
import useViewModel from "./vm";
import ListContainer from '@mui/material/List';
import Item from '@mui/material/ListItem';
import Button from '@mui/material/ListItemButton';
import Text from '@mui/material/ListItemText';
import useFetchCategory from "../../../dataSource/useFetchCategory";
import useFetchItems from "../../../dataSource/useFetchItems";

const SubCategoriesMenu = ()=>{
    const viewModel = useViewModel()
    const fetchCategory = useFetchCategory()
    const fetchItems = useFetchItems()
    const onClick = (selfUrl: string, itemsUrl: string)=>()=>{
        fetchCategory(selfUrl)
        fetchItems(itemsUrl)
    }
    return (
        <Paper elevation={1}>
            <ListContainer>
                {viewModel?.subCategories.map((subcategory, index)=>(
                    <Item key={index}>
                        <Button onClick={onClick(subcategory._links.self.href, subcategory._links.items.href)}><Text>{subcategory.name}</Text></Button>
                    </Item>
                ))}
            </ListContainer>
        </Paper>
    )
}
export default SubCategoriesMenu