import {useAppDispatch, useAppSelector} from "../../features/Hooks";
import ItemsAction from "../../features/items/ItemsAction";

const useFetchItems = ()=>{
    const appDispatch = useAppDispatch()

    return (url: string)=>{ 
        appDispatch(ItemsAction.getAll.of.async(
            url
        ))
    }
    
}

export default useFetchItems
