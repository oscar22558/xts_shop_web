import { Button, ButtonProps } from "@mui/material"
import { styled } from "@mui/material/styles"

const LowerCaseButton = styled(Button)<ButtonProps>(({theme})=>({
    textTransform: "none"
}))
export default LowerCaseButton