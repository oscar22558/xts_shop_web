import { Box } from "@mui/material";
import { Link } from "react-router-dom";
import useFetchCategory from "../../../../../data-sources/category/useFetchCategory";
import useFetchItems from "../../../../../data-sources/items/useFetchItems";
import useViewModel from "./useViewModel";

const CategoriesStack = ()=>{
    const viewModel = useViewModel()
    const fetchCategory = useFetchCategory()
    const fetchItems = useFetchItems()
    return (
        <Box sx={{paddingY: "10px", paddingLeft: "30px", width: "100%"}}>
            {viewModel?.map(({url, id, name}, index)=>(
                <Box key={index} sx={{display: "inline"}}>
                    {index !== 0 && <Box sx={{display: "inline", marginX: "10px"}}>{">"}</Box>}
                    <Link to={url} onClick={()=>{
                        fetchCategory(id)
                        fetchItems(id)
                    }}>{name}</Link>
                </Box>
            ))}
        </Box>
    )
}

export default CategoriesStack