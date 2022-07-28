import * as React from 'react';
import {styled, Tabs} from "@mui/material";

interface StyledTabsProps {
    children?: React.ReactNode;
    value: number|boolean;
    onChange: (event: React.SyntheticEvent, newValue: number) => void;
}

const StyledTabs = styled((props: StyledTabsProps) => (
    <Tabs
        {...props}
        TabIndicatorProps={{ children: <span className="MuiTabs-indicatorSpan" /> }}
        
    />
))({
    '& .MuiTabs-indicator': {
        display: 'flex',
        justifyContent: 'center',
        backgroundColor: 'transparent',
    },
    '& .MuiTabs-indicatorSpan': {
        width: '100%',
        backgroundColor: 'rgba(255, 255, 255, 1.0)',
    },
    "& .MuiTabs-flexContainer": {
        backgroundColor: "rgba(25,118,210, 1.0)"
    }
});
export default StyledTabs