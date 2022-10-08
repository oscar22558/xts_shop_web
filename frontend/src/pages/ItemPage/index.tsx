import { Box, Divider } from "@mui/material"
import { useEffect } from "react"
import { useParams } from "react-router-dom"
import { useAppDispatch } from "../../features/Hooks"
import ItemDetailAction from "../../features/item-detail/ItemDetailAction"
import ItemDetailSectionContainer from "./ItemDetailSectionContainer"

const ItemPage = ()=>{
    const routeParams = useParams()
    const dispatch = useAppDispatch()

    useEffect(()=>{
        const {itemId} = routeParams
        if(!itemId) return
        dispatch(ItemDetailAction.async(Number.parseInt(itemId)))
    }, [routeParams, dispatch])
    return <Box>
        <ItemDetailSectionContainer />
        <Box sx={{paddingY: "20px"}}>Item Description</Box>
        <Divider />
        <Box sx={{paddingY: "20px"}}>Other items</Box>
        <Divider />
    </Box>
}
export default ItemPage