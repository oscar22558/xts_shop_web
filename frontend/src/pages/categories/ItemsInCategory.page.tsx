import React, { useState } from "react"
import {Button, Grid, IconButton, Select, Stack, styled} from "@mui/material";
import Box from "@mui/material/Box";
import SubCategoriesMenu from "./views/SubCategoriesMenu/SubCategoriesMenu";
import CategoriesStack from "./views/CategoriesStack/CategoriesStack";
import ItemCard from "./views/ItemCard/ItemCard";
import useViewModel from "./useViewModel";
import ItemsSortingOptionFilter from "./views/ItemsSortingOptionFilter/ItemsSortingOptionFilter";
import ItemListPagination from "./views/ItemListPagination/ItemListPagination";
import PriceFilter from "./views/PriceFilter/PriceFilter";
import BrandFilter from "./views/BrandFilter/BrandFilter";

const ItemsInCategoryPage = ()=>{
    const data = useViewModel()
    return (
        <Grid container spacing={2}>
            <Grid container xs={12} sx={{marginTop: "30px", marginBottom: "15px"}}>
                <Grid item xs={3} sx={{border: "1px solid", borderColor: "#0ff"}}><span /></Grid>
                <Grid item container xs={8} sx={{"border": "1px solid", borderColor: "#000"}}>                    
                    <Grid item xs={5} sx={{border: "1px solid", borderColor: "#f00"}}>
                        <CategoriesStack />
                    </Grid>
                </Grid>
                <Grid item xs={1} sx={{display: "flex", justifyContent: "flex-end", border: "1px solid", borderColor: "#00f"}}>
                    <ItemsSortingOptionFilter />
                </Grid>
            </Grid>
            <Grid container xs={12}>
                <Grid item xs={3}>
                    <Stack  spacing={2}>
                        <SubCategoriesMenu />
                        <BrandFilter />
                        <PriceFilter />
                    </Stack>
                </Grid>
                <Grid item xs={9}>
                    <Box sx={{flexDirection: "row", display: "flex", marginLeft: "30px"}}>
                        {data.map((vm, index)=>(<ItemCard key={index} index={index} />))}
                    </Box>
                </Grid>
            </Grid>
            <Grid container xs={12} sx={{ border: "1px solid", borderColor: "#0f0", display: "flex", justifyContent: "center"}}>
                <ItemListPagination />
            </Grid>
        </Grid>
    )
}
export default ItemsInCategoryPage