import {useAppDispatch} from "../../redux/Hooks";
import CategoriesActions from "../../redux/categories/CategoriesAction";

const useFetchCategory = () => {
    const appDispatch = useAppDispatch()
    return (url: string)=>{ appDispatch(CategoriesActions.getCategory.async(url)) }
}

export default useFetchCategory
