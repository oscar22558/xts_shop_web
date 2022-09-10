import {useEffect} from "react";
import {useAppSelector} from "../../features/Hooks";
import routesSelector from "../../features/api-routes/ApiRoutesSelector"
import useFetchBrands from "./useFetchBrands";

const FetchBrands = () => {
    const { data } = useAppSelector(routesSelector).get
    const fetch = useFetchBrands()
    useEffect(()=>fetch(), [data])
    return <></>
}
export default FetchBrands