import { ComponentType } from "react"
import { FormHelperText, TextField, TextFieldProps } from "@mui/material"
import StyledTextField from "./StyledTextField"
import { StyledComponent } from "@emotion/styled"

type withFormControlPropsType = {
    error?: TextFieldProps["error"]
    errorText?: string
}
const withFormControl = <P extends object>(
    Component: StyledComponent<P>
): ComponentType<P & withFormControlPropsType>=>{
   return (props)=>{
        const isError = props.error
        const errorText = props.errorText

        return (<>
            <Component {...props}/>
            {
                isError 
                ? <FormHelperText error>{errorText}</FormHelperText>
                : undefined
            }
        </>)
   } 
}
export default withFormControl(StyledTextField)
