import { Box, Checkbox } from "@mui/material"

type Props = {
    isChecked: boolean
    setIsChecked?: (isChecked: boolean)=>void
}

const ItemCheckBox = ({isChecked, setIsChecked}: Props)=>{
    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setIsChecked && setIsChecked(!isChecked)
    }
    return <Box display="flex" alignItems="center" justifyContent="center">
        <Checkbox checked={isChecked} onChange={handleChange} inputProps={{'aria-label': "controlled"}}/>
    </Box>
}

export default ItemCheckBox