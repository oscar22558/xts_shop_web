import * as React from 'react';
import {styled, Tab} from "@mui/material";

interface StyledTabProps {
    label: string
    onClick: ()=>void
}

const StyledTab = styled((props: StyledTabProps) => (
    <Tab {...props}/>
))(({ theme }) => ({
    textTransform: 'none',
    fontWeight: theme.typography.fontWeightRegular,
    fontSize: theme.typography.pxToRem(15),
    marginRight: theme.spacing(1),
    color: 'rgba(255, 255, 255, 0.7)',
    '&.Mui-selected': {
        color: '#fff',
    },
    '&.Mui-focusVisible': {
        backgroundColor: 'rgba(100, 95, 228, 0.32)',
    },
}));


export default StyledTab