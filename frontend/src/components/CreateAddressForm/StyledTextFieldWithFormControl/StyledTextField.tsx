import { styled, TextField, TextFieldProps } from "@mui/material"

const StyledTextField = styled(TextField)<TextFieldProps>(({theme})=>({
    width: "100%"
}))
export default StyledTextField