import {useAppDispatch} from "../redux/hooks";
import {useEffect} from "react";
import actions from "../redux/apiRoutes/action";
const FetchApiRoutes = () => {
    const appDispatch = useAppDispatch()
    useEffect(()=>{
        appDispatch(actions.get.async())
    }, [])
    return <></>
}
export default FetchApiRoutes
