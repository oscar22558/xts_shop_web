import {useAppDispatch} from "../../features/Hooks";
import CategoriesActions from "../../features/categories/CategoriesAction";

const useFetchCategory = () => {
    const appDispatch = useAppDispatch()
    return (categoryId: number)=>{ appDispatch(CategoriesActions.getCategory.async(categoryId)) }
}

export default useFetchCategory
