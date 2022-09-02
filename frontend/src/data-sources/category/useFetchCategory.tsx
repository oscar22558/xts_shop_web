import {useAppDispatch} from "../../features/Hooks";
import CategoriesActions from "../../features/categories/CategoriesAction";

const useFetchCategory = () => {
    const appDispatch = useAppDispatch()
    return (url: string)=>{ appDispatch(CategoriesActions.getCategory.async(url)) }
}

export default useFetchCategory
