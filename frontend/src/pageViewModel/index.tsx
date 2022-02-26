import {useAppSelector} from "../redux/hooks";
import selector from "../redux/items/selector"
const useViewModel = ()=> useAppSelector(selector).all.of.data
export default useViewModel
