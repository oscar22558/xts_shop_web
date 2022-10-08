import { Box } from "@mui/material"
import { useEffect } from "react"
import { useAppDispatch } from "../../../features/Hooks"
import ItemsAction from "../../../features/items/ItemsAction"
import ItemList from "../ItemsInCategoryPage/views/ItemList"

const HomePage = ()=>{
    const dispatch = useAppDispatch()
    useEffect(()=>{
        dispatch(ItemsAction.getAll.of.async(-1))
    }, [])
    return <Box sx={{paddingY: "40px"}}>
        <ItemList />
    </Box>
}
export default HomePage