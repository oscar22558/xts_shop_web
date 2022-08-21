import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { List, ListItemButton, ListItemText, Paper } from "@mui/material"
import AppRouteList from "../../../routes/AppRouteList"

const SettingMenu = ()=>{

    const navigate = useNavigate()

    const [selectedIndex, setSelectedIndex] = useState(0)

    const handleAccountItemClick = ()=>()=>{
        navigate(AppRouteList.settings.account)
    }

    const handleAddressesItemClick = ()=>()=>{
        navigate(AppRouteList.settings.addresses)
    }

    const setSelectedListItemIndex = (index: number)=>(handleItemClick?: ()=>void)=>()=>{
        setSelectedIndex(index)
        handleItemClick && handleItemClick()
    }

    const viewModel = [
        {
            label: "Account",
            handleClick: handleAccountItemClick
        },
        {
            label: "Addresses",
            handleClick: handleAddressesItemClick
        }
    ]

    return <Paper sx={{padding: "10px"}}>
        <List>
           {
                viewModel.map((itemData, index)=>(
                    <ListItemButton 
                        key={index}
                        selected={selectedIndex === index}
                        onClick={setSelectedListItemIndex(index)(itemData.handleClick())}
                    >
                        <ListItemText primary={itemData.label}/>
                    </ListItemButton>
                ))
            }
        </List>
    </Paper>
}
export default SettingMenu