import { useEffect } from "react"
import { useParams } from "react-router-dom"

import { Box } from "@mui/material"

import { useAppDispatch, useAppSelector } from "../../redux/Hooks"
import { GetOrderAction } from "../../redux/order/OrderAction"
import OrderSelector from "../../redux/order/OrderSelector"
import OrderItem from "../OrdersPage/OrderItem"

const OrderDetailPage = ()=>{
    const { orderId } = useParams()
    const dispatch = useAppDispatch()
    const { data: order, error } = useAppSelector(OrderSelector).getOrderResponse

    const convertToCurrencyViewText = (amount?: number)=>(
        "HKD $"+ (amount != null ? Math.round(amount*100)/100 : undefined)
    )

    const invoice = order?.invoice
    const orderSummaryViewModel = [
        {label: "Item total:", value: invoice?.subItemTotal},
        {label: "Shipping Fee:", value: invoice?.shippingFee},
        {label: "Total:", value: invoice?.total},
    ].map(viewModel=>({...viewModel, value: convertToCurrencyViewText(viewModel.value)}))
    
    const address = order?.shippingAddress
    const addressViewModel = address ? [
        address.row1, 
        address.row2 ? address.row2 : "-", 
        address.district, 
        address.area
    ] : []
    
    const itemViewModels = order?.items.map(item=>({...item, quantity: item.quantity.toString()}))
    useEffect(()=>{
        if(!orderId) return
        dispatch(GetOrderAction.async(Number.parseInt(orderId)))
    }, [orderId, dispatch])

    return <>{
        orderId == null ? undefined : (
            error != null 
                ?  <div>{error}</div> 
                : <> 
                <Box sx={{display: "flex", flexDirection: "row", alignItems: "center", justifyContent: "space-between"}}>
                    <h1>Order Detail</h1>
                    <Box sx={{padding: "10px"}}>Order id # {order?.id}</Box>
                </Box>
                    <Box sx={{border: "1px solid #adadad", borderRadius: "10px", paddingY: "15px", paddingX: "20px", display: "flex", justifyContent: "space-between"}}>
                        <Box sx={{display: "flex", flexDirection: "column"}}>
                            <div>Order Placed on</div>
                            <div>{order?.orderPlaced}</div>
                        </Box>
                        <Box>
                            <Box sx={{paddingBottom: "10px"}}>Shipping address</Box>
                            {addressViewModel.map((rowViewModel, index)=>(
                                <Box key={index}>{rowViewModel}</Box>
                            ))}
                        </Box>
                        <Box sx={{width: "300px", marginLeft: "50px"}}>
                            <Box sx={{paddingBottom: "10px"}}>Order summary</Box>
                            {orderSummaryViewModel.map((viewModel, index)=>(
                                <Box key={index} sx={{display: "flex", width: "100%"}}>
                                    <Box sx={{flex: 1}}>{viewModel.label}</Box>
                                    <Box sx={{flex: 1}}>{viewModel.value}</Box>
                                </Box>
                            ))}
                        </Box>
                    </Box>
                    <Box sx={{border: "1px solid #adadad", borderRadius: "10px", paddingY: "15px", paddingX: "20px", marginTop: "20px"}}>
                        {itemViewModels?.map((item, index)=>(<OrderItem key={index} {...item}/>))}
                    </Box>
                </>
        )
    }
    </>
}
export default OrderDetailPage