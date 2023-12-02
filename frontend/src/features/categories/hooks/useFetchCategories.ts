import {useAppDispatch} from "../../Hooks";
import CategoriesActions from "../CategoriesAction";
import { useEffect } from "react";

const useFetchCategories = () => {
    const dispatch = useAppDispatch()
    useEffect(()=>{
        dispatch(CategoriesActions.getAllCategories.async())
    }, [dispatch])
}
export default useFetchCategories