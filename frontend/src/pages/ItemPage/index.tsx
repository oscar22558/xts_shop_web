import { Box, Divider } from "@mui/material"
import { useEffect } from "react"
import { useParams } from "react-router-dom"
import { useAppDispatch, useAppSelector } from "../../features/Hooks"
import ItemDetailAction from "../../features/item-detail/ItemDetailAction"
import ItemDetailSelector from "../../features/item-detail/ItemDetailSelector"
import ItemDetailSectionContainer from "./ItemDetailSectionContainer"

const ItemPage = ()=>{
    const routeParams = useParams()
    const dispatch = useAppDispatch()
    const item = useAppSelector(ItemDetailSelector).getItemDetailResponse.data

    useEffect(()=>{
        const {itemId} = routeParams
        if(!itemId) return
        dispatch(ItemDetailAction.async(Number.parseInt(itemId)))
    }, [routeParams, dispatch])
    return <Box>
        <ItemDetailSectionContainer />
        <Box sx={{paddingY: "20px"}}>Item Description</Box>
        <Divider />
        <Box sx={{paddingY: '20px'}}>
            <Box>{item?.description ?? ""}</Box>
        </Box>
    </Box>
}
export default ItemPage