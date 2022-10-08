import { useAppSelector } from "../../../features/Hooks"
import ItemDetailSelector from "../../../features/item-detail/ItemDetailSelector"
import ItemDetailSection from "./ItemDatilSection"

const ItemDetailSectionContainer = ()=>{
    const {data: item} = useAppSelector(ItemDetailSelector).getItemDetailResponse
    return (
       item ? <ItemDetailSection {...item} /> : <></>
    )
}
export default ItemDetailSectionContainer