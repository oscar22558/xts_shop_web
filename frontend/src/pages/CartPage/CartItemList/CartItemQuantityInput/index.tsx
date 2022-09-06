import useCart from "../../../../data-sources/cart/useCart";
import QuantityInput from "../../../../components/QuantityInput";

type Props = {
    itemId: number
}

const CartItemQuantityInput = ({itemId}: Props)=>{
    const {itemCountsInCart, addCartItem, minusItemQunity} = useCart()
    const itemCount = itemCountsInCart[itemId]
    const handleRemoveBtnClick = ()=>{
        addCartItem(itemId)
    }

    const handleAddBtnClick = ()=>{
        minusItemQunity(itemId)
    }

    return <QuantityInput onAddBtnClick={handleAddBtnClick} onRemoveBtnClick={handleRemoveBtnClick} quantity={itemCount}/>
}
export default CartItemQuantityInput