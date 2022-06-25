import {useAppDispatch} from "../redux/hooks";
import actions from "../redux/items/action";
import FetchItemOptions from "./dto/FetchItemOptions";

const useFetchItems = ()=>{
    const appDispatch = useAppDispatch()

    return (option?: FetchItemOptions) =>(url: string)=>{ 

        const getUrl = ()=>{
            let urlWithFilter = url

            urlWithFilter += "?sortingField=price"
            urlWithFilter += "&sortingDirection=asc"
            urlWithFilter = option?.filter.maxPrice != null ? (
                urlWithFilter+"&maxPrice="+option?.filter.maxPrice
            ): urlWithFilter
            
            urlWithFilter = option?.filter.minPrice != null ? (
                urlWithFilter+"&minPrice="+option?.filter.minPrice
            ): urlWithFilter
            return urlWithFilter
        }

        appDispatch(actions.getAll.of.async(
            getUrl()
        ))
    }
    
}

export default useFetchItems
