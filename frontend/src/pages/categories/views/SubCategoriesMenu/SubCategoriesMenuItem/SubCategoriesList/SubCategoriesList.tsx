import { Box } from "@mui/material"
import SubCategoriesListItem from "./SubCategoriesListItem/SubCategoriesListItem"
import useViewModel from "./useViewModel"
interface Props{
    index: number
}
const SubCategoriesList = ({
    index
}: Props)=>{
    const viewModel = useViewModel(index)
    return <Box sx={{height: "100px"}}>
        {viewModel.categories?.map((category, index)=>(
            <SubCategoriesListItem label={category.name} onClick={viewModel.onClick(category._links.self.href, category._links.items.href)}/>
        ))}
    </Box>
}
export default SubCategoriesList