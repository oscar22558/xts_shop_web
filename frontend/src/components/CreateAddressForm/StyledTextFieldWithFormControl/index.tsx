import { ComponentType } from "react"
import { FormHelperText, TextFieldProps } from "@mui/material"
import StyledTextField from "./StyledTextField"
import { StyledComponent } from "@emotion/styled"

type withFormControlPropsType = {
    error?: TextFieldProps["error"]
    errorText?: string
}
const withFormControl = <P extends object>(
    Component: StyledComponent<P>
): ComponentType<P & withFormControlPropsType>=>{
   return ({error: isError, errorText, ...props})=>{
        return (<>
            <Component {...props as P}/>
            {
                isError 
                ? <FormHelperText error>{errorText}</FormHelperText>
                : undefined
            }
        </>)
   } 
}
export default withFormControl(StyledTextField)
