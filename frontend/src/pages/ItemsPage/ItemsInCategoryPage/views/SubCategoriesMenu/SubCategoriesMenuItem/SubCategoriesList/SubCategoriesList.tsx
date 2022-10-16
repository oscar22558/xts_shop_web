import { Box } from "@mui/material"
import SubCategoriesListItem from "./SubCategoriesListItem/SubCategoriesListItem"
import useViewModel from "./useViewModel"

interface Props{
    index: number
}

const SubCategoriesList: React.FC<Props> = ({
    index
})=>{
    const viewModel = useViewModel(index)
    return viewModel.categories && viewModel.categories.length > 0 
        ? <Box sx={{border: "1px solid rgba(0,0,0,0.1)", borderRadius: "5px", width: "100%"}}>
            {viewModel.categories?.map(({id, name}, index)=>(
                <SubCategoriesListItem 
                    key={index}
                    label={name} 
                    onClick={viewModel.onClick(id)}
                />
            ))
            }
        </Box>
        : <></>
}
export default SubCategoriesList