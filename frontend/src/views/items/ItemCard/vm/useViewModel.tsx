import {useAppSelector} from "../../../../redux/hooks";
import selector from "../../../../redux/items/selector"
import {domain, port, protocol} from "../../../../redux/apiConfig"
const useViewModel = (index: number)=>{
    const data = useAppSelector(selector).all.of.data[index]
    return {...data, imgUrl: `${protocol}://${domain}:${port}/${data.imgUrl}`}

}
export default useViewModel