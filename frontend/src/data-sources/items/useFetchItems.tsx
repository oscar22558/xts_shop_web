import { useCallback } from "react";
import {useAppDispatch} from "../../features/Hooks";
import ItemsAction from "../../features/items/ItemsAction";

const useFetchItems = ()=>{
    const dispatch = useAppDispatch()
    const fetchItems = useCallback((categoryId: number)=>{ 
        console.log("fetch item callback executed")
        dispatch(ItemsAction.getAll.of.async(categoryId))
    }, [dispatch])
    
    return fetchItems
}

export default useFetchItems
