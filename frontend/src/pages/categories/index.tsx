import React, { useState } from "react"
import {Button, Grid, IconButton, styled} from "@mui/material";
import Box from "@mui/material/Box";
import SubCategoriesMenu from "../../views/categories/SubCategoriesMenu/SubCategoriesMenu";
import CategoriesStack from "../../views/categories/CategoriesStack/CategoriesStack";
import ItemCard from "../../views/items/ItemCard/ItemCard";
import useViewModel from "../../pageViewModel";

const Categories = ()=>{
    const data = useViewModel()
    return (
        <Grid container spacing={2}>
            <Grid container xs={12} sx={{ border: "solid 1px #f00", marginTop: "30px", marginBottom: "15px"}}>
                <Grid item xs={3}><span /></Grid>
                <Grid item xs={9}>
                    <Box sx={{marginLeft: "30px"}}>
                        <CategoriesStack />
                    </Box>
                </Grid>
            </Grid>
            <Grid container xs={12}>
                <Grid item xs={3}>
                    <Box sx={{  paddingLeft: "30px"}}>
                        <SubCategoriesMenu />
                    </Box>
                </Grid>
                <Grid item xs={9}>
                    <Box sx={{flexDirection: "row", display: "flex", marginLeft: "30px"}}>
                        {data.map((vm, index)=>(<ItemCard key={index} index={index} />))}
                    </Box>
                </Grid>
            </Grid>
        </Grid>
    )
}
export default Categories