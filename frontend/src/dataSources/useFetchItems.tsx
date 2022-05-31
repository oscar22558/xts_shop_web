import {useAppDispatch} from "../redux/hooks";
import actions from "../redux/items/action";

const useFetchItems = () => {
    const appDispatch = useAppDispatch()
    return (url: string)=>{ appDispatch(actions.getAll.of.async(url)) }
}

export default useFetchItems
