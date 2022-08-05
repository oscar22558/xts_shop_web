import { Box } from "@mui/material"
import { useEffect } from "react"
import { useAppDispatch, useAppSelector } from "../../redux/Hooks"
import { OrdersAction } from "../../redux/order/OrderAction"
import OrderSelector from "../../redux/order/OrderSelector"

const OrdersPage = ()=>{
    const orderList = useAppSelector(OrderSelector).getOrderListResponse.data
    const dispatch = useAppDispatch()
    useEffect(()=>{
        dispatch(OrdersAction.async())
    }, [])
    return <>{
        orderList.map(order=><Box>
            <div>{order.id}</div>
            <div>{order.orderStatus}</div>
        </Box>)
    }</>
}
export default OrdersPage