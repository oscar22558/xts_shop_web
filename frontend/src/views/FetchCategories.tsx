import useFetchCategories from "../dataSources/useFetchCategories";
import {useEffect} from "react";
import {useAppSelector} from "../redux/hooks";
import routesSelector from "../redux/apiRoutes/selector"
const FetchCategories = () => {
    const { data } = useAppSelector(routesSelector).get
    const fetch = useFetchCategories()
    useEffect(()=>fetch(), [data])
    return <></>
}
export default FetchCategories
