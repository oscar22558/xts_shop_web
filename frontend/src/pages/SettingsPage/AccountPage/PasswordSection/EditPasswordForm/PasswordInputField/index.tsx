import { VisibilityOff, Visibility } from "@mui/icons-material"
import { OutlinedInput, InputAdornment, IconButton, FormControl, InputLabel, FormHelperText } from "@mui/material"
import React, { useState } from "react"

type Props = {
    error?: boolean
    errorText?: string
    label?: string
    value: string|null
    onChange?: (value: string|null)=>void
}

const PasswordInputField = ({
    label = "Password",
    error = false,
    errorText = "",
    value,
    onChange
}: Props)=>{
    const [showPassword, setShowPassword] = useState(false)

    const handleClickShowPassword = ()=>{
        setShowPassword(!showPassword)   
    }

    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    }

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>)=>{
        const value = event.target.value
        onChange && onChange(value)
    }

    return <FormControl>
        <InputLabel error={error} htmlFor="outlined-adornment-password">{label}</InputLabel>
        <OutlinedInput 
            title={label} 
            label={label}
            error={error}
            type={showPassword ? "text" : "password"}
            onChange={handleChange}
            value={value}
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
        {error ? <FormHelperText>{errorText}</FormHelperText> : undefined}
    </FormControl>}
export default PasswordInputField