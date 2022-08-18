import { Box, Button, Divider, Grid } from "@mui/material"
import { useEffect, useState } from "react"
import useCart from "../../../../data-sources/cart/useCart"
import RemoveItemCheckBox from "../../RemoveItemCheckBox"

type Props = {
    data: { ids: number[]}
    rowContent?: (id: number, isChecked: boolean, index: number)=>(JSX.Element | JSX.Element[] | undefined | null)
}

const CheckBoxList = ({data, rowContent}: Props)=>{
    const [checkBoxStates, setCheckBoxStates] = useState<{id: number, isChecked: boolean}[]>([])
    const [isSelectAllChecked, setIsSelectAllChecked] = useState(false)
    const [isCheckBoxsShown, setIsCheckBoxsShown] = useState(false)
    const { removeItems } = useCart()

    useEffect(()=>{
        const initialCheckBoxStates = data.ids.map((id)=>({id, isChecked: false}))
        setCheckBoxStates(initialCheckBoxStates)
    }, [data.ids])

    const isSelectAllBtnShown = isCheckBoxsShown

    const unselectAllCheckBox = ()=>{
        const newStates = checkBoxStates.map((state)=>{
            return {...state, isChecked: false}
        })
        setCheckBoxStates(newStates)
    }

    const selectAllCheckBox = ()=>{
        const newStates = checkBoxStates.map((state)=>{
            return {...state, isChecked: true}
        })
        setCheckBoxStates(newStates)
    }

    const checkIsCheckedByItemId = (itemId: number)=>{
        return checkBoxStates.find((state)=>state.id === itemId)?.isChecked ?? false
    }

    const setItemIdForCheckBoxOnChangeHandler = (itemId: number)=>(isChecked: boolean)=>{
        const newStates = checkBoxStates.map((state)=>{
            if(state.id === itemId){
                return {...state, isChecked}
            }
            return state
        })
        setCheckBoxStates(newStates)
    }
    const handleSelectAllBtnClick = (isChecked: boolean)=>{
        setIsSelectAllChecked(isChecked)
        if(isChecked){
            selectAllCheckBox()
            return
        }
        unselectAllCheckBox()
    }

    const handleEditBtnClick = ()=>{
        if(isCheckBoxsShown){
            unselectAllCheckBox() 
            setIsSelectAllChecked(false)
        }
        setIsCheckBoxsShown(!isCheckBoxsShown)
    }

    const handleDeleteBtnClick = ()=>{
        unselectAllCheckBox() 
        setIsCheckBoxsShown(false)
        const selectedIds = checkBoxStates.filter(state=>state.isChecked).map(state=>state.id)
        removeItems(selectedIds)
    }

    return (
        <Box sx={{paddingRight: "25px"}}>
            <Grid container direction="row" columns={24} height="50px">
                <Grid item xs={2} sx={{display: "flex", justifyContent: "center", alignItems: "center"}}>
                    {isSelectAllBtnShown && 
                        <RemoveItemCheckBox 
                            isChecked={isSelectAllChecked}
                            setIsChecked={handleSelectAllBtnClick}
                        />
                    }
                </Grid>
                <Grid item xs={11} sx={{display: "flex", justifyContent: "flex-start"}}>
                    {isCheckBoxsShown && <Button onClick={handleDeleteBtnClick}>Delete</Button>}
                </Grid>
                <Grid item xs={11} sx={{display: "flex", justifyContent: "flex-end"}}>
                    <Button onClick={handleEditBtnClick}>{isCheckBoxsShown ? "Cancel": "Edit"}</Button>
                </Grid>
            </Grid>
            <Divider />
            {data.ids.map((id, index)=>
                <Box key={index}>
                    <Box sx={{paddingY: "15px"}}>
                        <Grid container direction="row" columns={24}>
                            <Grid item xs={2}>
                                {isCheckBoxsShown 
                                    && <RemoveItemCheckBox 
                                        isChecked={checkIsCheckedByItemId(id)}
                                        setIsChecked={setItemIdForCheckBoxOnChangeHandler(id)}
                                    /> 
                                }
                            </Grid>
                            <Grid item xs={22}>
                                {rowContent && rowContent(id, checkIsCheckedByItemId(id), index)}
                            </Grid>
                        </Grid>
                    </Box>
                    <Divider />
                </Box>
            )}
        </Box>
    )
}
export default CheckBoxList