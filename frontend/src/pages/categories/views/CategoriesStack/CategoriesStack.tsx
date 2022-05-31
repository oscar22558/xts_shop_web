import { Link } from "react-router-dom";
import useFetchCategory from "../../../../dataSources/useFetchCategory";
import useViewModel from "./useViewModel";

const CategoriesStack = ()=>{
    const viewModel = useViewModel()
    const fetchCategory = useFetchCategory()
    return (
        <div style={{paddingTop: "10px", paddingBottom: "10px"}}>{viewModel?.map((categoryLink, index)=>(
             <>
                {index !== 0 && <span>{">"}</span>}
                <Link key={index} to={categoryLink.url} onClick={()=>{
                    fetchCategory(categoryLink.apiUrl)
                }}>{categoryLink.name}</Link>
            </>
        ))}</div>
    )
}

export default CategoriesStack