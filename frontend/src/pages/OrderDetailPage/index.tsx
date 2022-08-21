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
                </>
        )
    }
    </>
}
export default OrderDetailPage