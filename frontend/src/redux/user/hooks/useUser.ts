import { useAppSelector } from "../../Hooks"
import UserSelector from "../UserSelector"

const useUser = ()=>{
    const {getUserResponse} = useAppSelector(UserSelector)
    return {
        user: getUserResponse.data
    }
}
export default useUser