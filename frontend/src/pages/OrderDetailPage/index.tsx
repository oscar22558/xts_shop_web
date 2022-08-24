import { useEffect } from "react"
import { useParams } from "react-router-dom"

import { Box } from "@mui/material"

import { useAppDispatch, useAppSelector } from "../../redux/Hooks"
import { GetOrderAction } from "../../redux/order/OrderAction"
import OrderSelector from "../../redux/order/OrderSelector"

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

    useEffect(()=>{
        if(!orderId) return
        dispatch(GetOrderAction.async(Number.parseInt(orderId)))
    }, [orderId, dispatch])

    return <>{
        orderId == null ? undefined : (
            error != null 
                ?  <div>{error}</div> 
                : <> 
                    <h1>Order Detail</h1>
                    <Box sx={{display: "flex", flexDirection: "row", padding: "10px"}}>
                        <div>Order id # {order?.id}</div>
                        <Box sx={{background: "#ddd", width: "1px", marginX: "30px"}} />
                        <Box>Order Placed on {order?.orderPlaced}</Box>
                    </Box>
                    <Box sx={{border: "1px solid #adadad", borderRadius: "10px", paddingY: "15px", paddingX: "20px", display: "flex", justifyContent: "space-between"}}>
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
                </>
        )
    }
    </>
}
export default OrderDetailPage