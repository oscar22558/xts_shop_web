import {useEffect} from "react";
import {useAppSelector} from "../redux/Hooks";
import routesSelector from "../redux/api-routes/ApiRoutesSelector"
import useFetchBrands from "../data-sources/useFetchBrands";

const FetchBrands = () => {
    const { data } = useAppSelector(routesSelector).get
    const fetch = useFetchBrands()
    useEffect(()=>fetch(), [data])
    return <></>
}
export default FetchBrands