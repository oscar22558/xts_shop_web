import { Select } from "@mui/material"
import React from "react"

const withSelectStyle = <P extends object>(
    Component: React.ComponentType<P>
): React.ComponentType<P>=>{
    const itemHeight = 48
    const itemPaddingTop = 8
    const MenuProps = {
        PaperProps: {
            style: {
            maxHeight: itemHeight * 4.5 + itemPaddingTop,
            width: 250,
            },
        },
    }
    return (props: P)=>(<Component {...props} MenuProps={MenuProps} />)
}
export default withSelectStyle(Select)