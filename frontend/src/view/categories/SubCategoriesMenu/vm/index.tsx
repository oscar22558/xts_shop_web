import {useAppDispatch, useAppSelector} from "../../../../redux/hooks";
import selector from "../../../../redux/categories/selector"
export const useViewModel = ()=>{
    const { data } = useAppSelector(selector).one
    return data
}
export default useViewModel