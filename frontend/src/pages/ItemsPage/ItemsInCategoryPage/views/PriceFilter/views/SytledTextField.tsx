import { styled, TextField, TextFieldProps } from "@mui/material"

const StyledTextField = styled(TextField)<TextFieldProps>(({theme}) => ({
    "& .MuiOutlinedInput-input": {maxHeight: "12px"}
}))
export default StyledTextField