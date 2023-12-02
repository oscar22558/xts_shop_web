import {Grid, Stack} from "@mui/material";
import Box from "@mui/material/Box";
import SubCategoriesMenu from "./views/SubCategoriesMenu/SubCategoriesMenu";
import CategoriesStack from "./views/CategoriesStack/CategoriesStack";
import ItemsSortingOptionFilter from "./views/ItemsSortingOptionFilter/ItemsSortingOptionFilter";
import ItemListPagination from "./views/ItemListPagination/ItemListPagination";
import PriceFilter from "./views/PriceFilter/PriceFilter";
import BrandFilter from "./views/BrandFilter/BrandFilter";
import ItemList from "./views/ItemList";

const ItemsInCategoryPage = ()=>{
    return (
        <Grid container spacing={2}>

            <Grid container sx={{marginTop: "30px", marginBottom: "15px"}}>
                <Grid item xs={3}><span /></Grid>
                <Grid item container xs={8}>                    
                    <CategoriesStack />
                </Grid>
                <Grid item xs={1} sx={{display: "flex", justifyContent: "flex-end"}}>
                    <ItemsSortingOptionFilter />
                </Grid>
            </Grid>
            <Grid container>
                <Grid item xs={3}>
                    <Stack  spacing={2}>
                        <SubCategoriesMenu />
                        <BrandFilter />
                        <PriceFilter />
                    </Stack>
                </Grid>
                <Grid item xs={9}>
                    <Box sx={{paddingLeft: "30px"}}>
                        <ItemList />
                    </Box>
                </Grid>
            </Grid>
            <Grid container sx={{ border: "1px solid", borderColor: "#0f0", display: "flex", justifyContent: "center"}}>
                <ItemListPagination />
            </Grid>
        </Grid>
    )
}
export default ItemsInCategoryPage