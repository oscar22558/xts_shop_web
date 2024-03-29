import {Box, Card} from "@mui/material";
import useViewModel from "./useViewModel";
import ListContainer from '@mui/material/List';
import SubCategoriesMenuItem from './SubCategoriesMenuItem/SubCategoriesMenuItem';
const SubCategoriesMenu = ()=>{
    const viewModel = useViewModel()
    return (
        <Card>
            <Box sx={{paddingTop: "20px",paddingLeft: "20px", paddingRight: "20px", fontSize: "20px", fontWeight: "bold"}}>Categories</Box>
            <ListContainer>
                {viewModel.data?.subCategories.map(({id, name}, index)=>(
                    <SubCategoriesMenuItem 
                        key={index}
                        index={index}
                        label={name}
                        onClick={viewModel.onClick(id)} 
                    />
                ))}
            </ListContainer>
        </Card>
    )
}
export default SubCategoriesMenu