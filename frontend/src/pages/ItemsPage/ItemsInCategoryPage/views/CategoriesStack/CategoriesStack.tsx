import { Box } from "@mui/material";
import { Link } from "react-router-dom";
import useFetchCategory from "../../../../../data-sources/useFetchCategory";
import useFetchItems from "../../../../../data-sources/useFetchItems";
import useViewModel from "./useViewModel";

const CategoriesStack = ()=>{
    const viewModel = useViewModel()
    const fetchCategory = useFetchCategory()
    const fetchItems = useFetchItems()
    return (
        <div style={{paddingTop: "10px", paddingBottom: "10px"}}>{viewModel?.map((itemModel, index)=>(
             <Box key={index}>
                {index !== 0 && <span>{">"}</span>}
                <Link to={itemModel.url} onClick={()=>{
                    fetchCategory(itemModel.apiUrl)
                    fetchItems(itemModel.itemUrl)
                }}>{itemModel.name}</Link>
            </Box>
        ))}</div>
    )
}

export default CategoriesStack