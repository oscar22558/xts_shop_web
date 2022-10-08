import {useAppSelector} from "../../../../../../features/Hooks";
import ItemsSelector from "../../../../../../features/items/ItemsSelector"
import {domain, port, protocol} from "../../../../../../features/ApiConfig"
import useCart from "../../../../../../data-sources/cart/useCart";

const useViewModel = (index: number)=>{
    const {addCartItem} = useCart()
    const data = useAppSelector(ItemsSelector).all.of.data[index]
    const handleAddItemToCartBtnClick = ()=>{
        addCartItem(data.id)
    }
    return {...data, imgUrl: `${protocol}://${domain}:${port}/${data.imgUrl}`, handleAddItemToCartBtnClick}

}
export default useViewModel