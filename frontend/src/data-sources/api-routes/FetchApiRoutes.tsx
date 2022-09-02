import {useAppDispatch} from "../../features/Hooks";
import {useEffect} from "react";
import ApiRoutesActions from "../../features/api-routes/ApiRoutesAction";
const FetchApiRoutes = () => {
    const appDispatch = useAppDispatch()
    useEffect(()=>{
        appDispatch(ApiRoutesActions.get.async())
    }, [])
    return <></>
}
export default FetchApiRoutes
