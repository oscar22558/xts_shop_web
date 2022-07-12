import {useAppSelector} from "../../../../../redux/Hooks";
import ItemsSelector from "../../../../../redux/items/ItemsSelector"
import {domain, port, protocol} from "../../../../../redux/ApiConfig"
import useCart from "../../../../../data-sources/cart/useCart";

const useViewModel = (index: number)=>{
    const {addCartItem} = useCart()
    const data = useAppSelector(ItemsSelector).all.of.data[index]
    const handleAddItemToCartBtnClick = ()=>{
        addCartItem(data.id)
    }
    return {...data, imgUrl: `${protocol}://${domain}:${port}/${data.imgUrl}`, handleAddItemToCartBtnClick}

}
export default useViewModel