import {useAppDispatch} from "../redux/hooks";
import actions from "../redux/categories/action";

const useFetchCategory = () => {
    const appDispatch = useAppDispatch()
    return (url: string)=>{ appDispatch(actions.get.async(url)) }
}

export default useFetchCategory
