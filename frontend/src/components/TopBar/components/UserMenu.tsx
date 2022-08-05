import { useState } from "react";

import { Menu } from "@mui/material"
import Avatar from '@mui/material/Avatar';
import Tooltip from '@mui/material/Tooltip';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import MenuItem from '@mui/material/MenuItem';

import useAuthentication from "../../../data-sources/authentication/useAuthentication";
import useDestroyAuthentication from '../../../data-sources/authentication/useDestroyAuthentication';
import { useNavigate } from "react-router-dom";
import AppRouteList from "../../../routes/AppRouteList";

const UserMenu = ()=>{
    const [anchorElUser, setAnchorElUser] = useState<null | HTMLElement>(null);

    const authentication = useAuthentication()
    const destroyAuthentication = useDestroyAuthentication()
    const navigate = useNavigate()

    const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElUser(event.currentTarget);
    };

    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
    };

    const handleOrdersClick = () => {
        handleCloseUserMenu()
        navigate(AppRouteList.orders)
    }

    const handleLogoutItemClick = ()=>{
        handleCloseUserMenu()
        destroyAuthentication()
    }

    const handleSettingClick = ()=>{
        handleCloseUserMenu()
        navigate(AppRouteList.settings.index)
    }

    const settingModels: {label: string, onClick: ()=>void}[] = [
        {label: "Orders", onClick: handleOrdersClick},
        {label: 'Settings', onClick: handleSettingClick}, 
        {label: 'Logout', onClick: handleLogoutItemClick}
    ];

    return (
        <>
        {authentication.isUserAuthenticated 
            ? <Tooltip title="Open settings">
                <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                    <Avatar alt="Remy Sharp" src="/static/images/avatar/2.jpg" />
                </IconButton>
            </Tooltip>
            : undefined
        }
        <Menu
            sx={{ mt: '45px' }}
            id="menu-appbar"
            anchorEl={anchorElUser}
            anchorOrigin={{
                vertical: 'top',
                horizontal: 'right',
            }}
            keepMounted
            transformOrigin={{
                vertical: 'top',
                horizontal: 'right',
            }}
            open={Boolean(anchorElUser)}
            onClose={handleCloseUserMenu}
        >
            {settingModels.map((settingModel, index) => (
                <MenuItem key={index} onClick={settingModel.onClick}>
                    <Typography textAlign="center">{settingModel.label}</Typography>
                </MenuItem>
            ))}
        </Menu>
        </>
    )
}
export default UserMenu