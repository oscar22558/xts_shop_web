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
        <div style={{paddingTop: "10px", paddingBottom: "10px"}}>{viewModel?.map(({url, id, name}, index)=>(
             <Box key={index}>
                {index !== 0 && <span>{">"}</span>}
                <Link to={url} onClick={()=>{
                    fetchCategory(id)
                    fetchItems(id)
                }}>{name}</Link>
            </Box>
        ))}</div>
    )
}

export default CategoriesStack