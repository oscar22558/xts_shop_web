import { Box } from "@mui/material"
import { useEffect } from "react"
import { useParams } from "react-router-dom"
import { useAppDispatch, useAppSelector } from "../../redux/Hooks"
import { GetOrderAction } from "../../redux/order/OrderAction"
import OrderSelector from "../../redux/order/OrderSelector"

const OrderDetailPage = ()=>{
    const { orderId } = useParams()
    const dispatch = useAppDispatch()
    const { data: order, error } = useAppSelector(OrderSelector).getOrderResponse

    useEffect(()=>{
        if(!orderId) return
        dispatch(GetOrderAction.async(Number.parseInt(orderId)))
    }, [orderId])

    return <>{
        orderId == null ? undefined : (
            error != null 
                ?  <div>{error}</div> 
                : <> 
                    <div>Order Detail Page</div>
                    <div>{order?.id}</div>
                    <div>{order?.orderPlaced}</div>
                    <Box sx={{border: "1px solid #fefefe", borderRadius: "10px"}}>
                        <div>Order invoce</div>
                        <div>{order?.invoice.subItemTotal}</div>
                        <div>{order?.invoice.shippingFee}</div>
                        <div>{order?.invoice.total}</div>
                    </Box>
                    <Box sx={{border: "1px solid #fefefe", borderRadius: "10px"}}>
                        <div>Shipping address</div>
                        <div>{order?.shippingAddress.row1}</div>
                        <div>{order?.shippingAddress.row2}</div>
                        <div>{order?.shippingAddress.district}</div>
                        <div>{order?.shippingAddress.area}</div>
                    </Box>
                </>
        )
    }
    </>
}
export default OrderDetailPage