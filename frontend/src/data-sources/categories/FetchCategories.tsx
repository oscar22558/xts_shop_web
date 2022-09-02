import useFetchCategories from "./useFetchCategories";
import {useEffect} from "react";
import {useAppSelector} from "../../features/Hooks";
import routesSelector from "../../features/api-routes/ApiRoutesSelector"

const FetchCategories = () => {
    const { data } = useAppSelector(routesSelector).get
    const fetch = useFetchCategories()
    useEffect(()=>fetch(), [data])
    return <></>
}
export default FetchCategories
