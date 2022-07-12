import {useAppSelector} from "../../../redux/Hooks";
import ItemsSelector from "../../../redux/items/ItemsSelector"
const useViewModel = ()=> useAppSelector(ItemsSelector).all.of.data
export default useViewModel
