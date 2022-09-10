import ItemFilter from "./ItemFilter"
import ItemSorting from "./ItemSorting"

//TODO: remove option on sorting
interface FetchItemOptions{
    filter: ItemFilter,
    sorting?: ItemSorting
}
export default FetchItemOptions