import type { NextPage } from 'next'
import {useAppDispatch, useAppSelector} from "../redux/hooks";
import {useEffect} from "react";
import {getAllAsync as categoriesGetAllAsync} from "../redux/categories/action";
import AppTopBar from "../components/TopBar/AppTopBar";
import routesSelector from "../redux/routes/selector"
const Index: NextPage = () => {
    const { get } = useAppSelector(routesSelector)
    const { data } = get
  const appDispatch = useAppDispatch()
  useEffect(()=>{
      if(data){
          appDispatch(categoriesGetAllAsync())
      }
  }, [data])
  return (
      <>
        <AppTopBar />
        <div>data fetched.</div>
      </>
  )
}
export default Index