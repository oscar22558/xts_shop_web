import { Box, Button, Container, Divider } from "@mui/material"
import { useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { host } from "../../redux/ApiConfig"
import { useAppDispatch, useAppSelector } from "../../redux/Hooks"
import { OrdersAction } from "../../redux/order/OrderAction"
import OrderSelector from "../../redux/order/OrderSelector"
import AppRouteList from "../../routes/AppRouteList"
import OrderDetailColumn from "./OrderDetailColumn"
import OrderItem from "./OrderItem"

const OrdersPage = ()=>{
    const orderList = useAppSelector(OrderSelector).getOrderListResponse.data
    const dispatch = useAppDispatch()
    const navigate = useNavigate()

    const handleViewDetailBtnClick = (orderId: number)=>()=>{
        const route = AppRouteList.order.replace(":orderId", orderId.toString())
        navigate(route)
    }

    useEffect(()=>{
        dispatch(OrdersAction.async())
    }, [])

    return <>
        <h1>Your Orders</h1>
    {
        orderList.map(order=>({
            columns: [
                {label: "Order Id",content: order.id.toString()},
                {label: "Order Status", content: order.orderStatus.toLocaleLowerCase()},
                {label: "Order Placed", content: order.orderPlaced},
                {label: "Order Total", content: "23"}
            ],
            orderId: order.id,
            items: order.items.map(item=>({...item, quantity: item.quantity.toString(), imgUrl: `${host}/${item.imgUrl}`}))
        })).map((viewModel, index)=> <Box key={index} sx={{borderRadius: "10px", overflow: "hidden", border: "1px solid #dedede"}}>
            <Box sx={{display: "flex", flexDirection: "row", backgroundColor: "#dedede", paddingY: "10px", paddingX: "20px"}}>{
                viewModel.columns.map((column, index)=>(<OrderDetailColumn key={index} {...column}/>))
            }</Box>
            <Box sx={{padding: "15px"}}>
                {viewModel.items.map((item, index)=>(<OrderItem key={index} {...item}/>))}
            </Box>
            <Divider />
            <Box sx={{padding: "10px", display: "flex", justifyContent: "flex-end"}}>
                <Button variant="contained" onClick={handleViewDetailBtnClick(viewModel.orderId)}>View Detail</Button>
            </Box>
        </Box>)
    }</>
}
export default OrdersPage