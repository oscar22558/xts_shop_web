import React, { ComponentProps, useState } from "react"
import { OutlinedInput, InputAdornment, IconButton, FormControl, InputLabel, FormHelperText } from "@mui/material"
import { VisibilityOff, Visibility } from "@mui/icons-material"

type Props = ComponentProps<typeof OutlinedInput> & {
    errorText?: string
    value: string
} 

const PasswordInputField = ({
    name = "password",
    title = "Password",
    label = "Password",
    autoComplete = "password",
    errorText = "",
    error,
    ...outlinedInputProps
}: Props)=>{
    const [showPassword, setShowPassword] = useState(false)

    const handleClickShowPassword = ()=>{
        setShowPassword(!showPassword)   
    }

    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    }


    return <FormControl>
        <InputLabel error={error} htmlFor="outlined-adornment-password">{label}</InputLabel>
        <OutlinedInput 
            {...outlinedInputProps}
            name={name}
            title={title}
            label={label}
            error={error}
            autoComplete={autoComplete}
            type={showPassword ? "text" : "password"}
            endAdornment={
                <InputAdornment position="end">
                    <IconButton
                        aria-label="toggle password visibility"
                        onClick={handleClickShowPassword}
                        onMouseDown={handleMouseDownPassword}
                        edge="end"
                    >
                        {showPassword ? <VisibilityOff /> : <Visibility />}
                    </IconButton>
                </InputAdornment>
            }
        />
        {error ? <FormHelperText error>{errorText}</FormHelperText> : undefined}
    </FormControl>
}
export default PasswordInputField