import useFetchCategories from "./useFetchCategories";
import {useEffect} from "react";
import {useAppSelector} from "../../redux/Hooks";
import routesSelector from "../../redux/api-routes/ApiRoutesSelector"

const FetchCategories = () => {
    const { data } = useAppSelector(routesSelector).get
    const fetch = useFetchCategories()
    useEffect(()=>fetch(), [data])
    return <></>
}
export default FetchCategories
