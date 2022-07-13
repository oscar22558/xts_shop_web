import {useAppDispatch, useAppSelector} from "../../redux/Hooks";
import ItemsAction from "../../redux/items/ItemsAction";

const useFetchItems = ()=>{
    const appDispatch = useAppDispatch()

    return (url: string)=>{ 
        appDispatch(ItemsAction.getAll.of.async(
            url
        ))
    }
    
}

export default useFetchItems
