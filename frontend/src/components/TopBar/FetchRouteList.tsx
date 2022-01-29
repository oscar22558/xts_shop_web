import type { NextPage } from 'next'
import {useAppDispatch, useAppSelector} from "../../redux/hooks";
import {useEffect} from "react";
import {getAsync} from "../../redux/routes/action";
import {getAsync as categoriesGetAsync} from "../../redux/categories/action";
import apiConfig from "../../redux/apiConfig";
const FetchRouteList: NextPage = () => {
    const appDispatch = useAppDispatch()
    useEffect(()=>{
        appDispatch(getAsync())
    }, [])
    return (<></>)
}
export default FetchRouteList
