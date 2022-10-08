import {useAppSelector} from "../../../../../features/Hooks";
import ItemsSelector from "../../../../../features/items/ItemsSelector"
const useViewModel = ()=> useAppSelector(ItemsSelector).all.of.data
export default useViewModel
