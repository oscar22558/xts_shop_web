import { Box, Button, Divider, Grid } from "@mui/material"
import { useState } from "react"
import useFetchCartItemsByIds from "../../../../data-sources/cart/useFetchCartItemsByIds"
import RemoveItemCheckBox from "../../RemoveItemCheckBox"

type Props = {
    data: { ids: number[]}
    rowContent?: (id: number, isChecked: boolean, index: number)=>(JSX.Element | JSX.Element[] | undefined | null)
}

const CheckBoxList = ({data, rowContent}: Props)=>{
    const initialCheckBoxStates = data.ids.map((id)=>({id, isChecked: false}))
    const [isCheckBoxsShown, setIsCheckBoxsShown] = useState(false)
    const [checkBoxStates, setCheckBoxStates] = useState<{id: number, isChecked: boolean}[]>(initialCheckBoxStates)
    const isSelectAllBtnShown = isCheckBoxsShown

    useFetchCartItemsByIds() 

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
    const selectCheckBoxByItemId = (itemId: number)=>{
        const newStates = checkBoxStates.map((state)=>{
            if(state.id === itemId){
                return {id: state.id, isChecked: !state.isChecked}
            }
            return state
        })
        setCheckBoxStates(newStates)
    }

    const setItemIdForCheckBoxOnChangeHandler = (itemId: number)=>()=>selectCheckBoxByItemId(itemId)

    const handleSelectAllBtnClick = selectAllCheckBox

    const handleEditBtnClick = ()=>{
        if(isCheckBoxsShown){
            unselectAllCheckBox() 
        }
        setIsCheckBoxsShown(!isCheckBoxsShown)
    }

    return (
        <Box sx={{paddingRight: "25px"}}>
            <Box display="flex" alignItems="flex-end" justifyContent="flex-end">
                {isSelectAllBtnShown && <Button onClick={handleSelectAllBtnClick}>Select All</Button>}
                <Button onClick={handleEditBtnClick}>Edit</Button>
            </Box>
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