import React from "react"
import {useRouter} from "next/router";
import {Grid} from "@mui/material";
import Box from "@mui/material/Box";
import SubCategoriesMenu from "../../view/categories/SubCategoriesMenu/SubCategoriesMenu";
import CategoriesStack from "../../view/categories/CategoriesStack/CategoriesStack";
import ItemCard from "../../view/items/ItemCard/ItemCard";
import useViewModel from "../../pageViewModel";

const Categories = ()=>{
    const data = useViewModel()
    return (
        <Grid container spacing={2}>
            <Grid container xs={12} sx={{ border: "solid 1px #f00", marginTop: "30px"}}>
                <Grid item xs={4}><span /></Grid>
                <Grid item xs={8}>
                    <CategoriesStack />
                </Grid>
            </Grid>
            <Grid container xs={12}>
                <Grid item xs={3}>
                    <Box sx={{  paddingLeft: "30px"}}>
                        <SubCategoriesMenu />
                    </Box>
                </Grid>
                <Grid item xs={1}>
                    <Box>
                        {data.map((vm, index)=>(<ItemCard key={index} index={index} />))}
                    </Box>
                </Grid>
                <Grid item xs={8}>
                    <div>hi</div>
                </Grid>
            </Grid>
        </Grid>
    )
}
export default Categories