import {useAppDispatch} from "../redux/Hooks";
import {useEffect} from "react";
import ApiRoutesActions from "../redux/api-routes/ApiRoutesAction";
const FetchApiRoutes = () => {
    const appDispatch = useAppDispatch()
    useEffect(()=>{
        appDispatch(ApiRoutesActions.get.async())
    }, [])
    return <></>
}
export default FetchApiRoutes
