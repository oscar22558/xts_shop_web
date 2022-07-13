import {useAppDispatch} from "../../redux/Hooks";
import CateorgiesActions from "../../redux/categories/CategoriesAction";

const useFetchCategory = () => {
    const appDispatch = useAppDispatch()
    return (url: string)=>{ appDispatch(CateorgiesActions.get.async(url)) }
}

export default useFetchCategory
