import { styled, TextField, TextFieldProps } from "@mui/material"

const StyledTextField = styled(TextField)<TextFieldProps>(({theme})=>({
    marginTop: "10px"
}))
export default StyledTextField