import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';

import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import IconButton from '@mui/material/IconButton';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import MenuItem from '@mui/material/MenuItem';

import ShoppingCartIcon from './components/ShoppingCartIcon';
import SignInIcon from './components/SignInIcon';
import useAuthentication from '../../data-sources/authentication/useAuthentication';
import AppRouteList from '../../routes/AppRouteList';
import UserMenu from './components/UserMenu';
import HomePageLogoBtn from './components/HomePageLogoBtn';

const pages = ['Products', 'Pricing', 'Blog'];

const ResponsiveAppBar = () => {
    const [anchorElNav, setAnchorElNav] = useState<null | HTMLElement>(null);

    const authentication = useAuthentication()
    const navigate = useNavigate()

    const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElNav(event.currentTarget);
    };

    const handleCloseNavMenu = () => {
        setAnchorElNav(null);
    };

    return (
        <AppBar position="static">
            <Container maxWidth="xl" sx={{height: "60px", display: "flex", flexDirection: "row", alignItems: "center", justifyContent: "stretch", paddingY: "10px"}}>
                    <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' }}}>
                        <IconButton
                            size="large"
                            aria-label="account of current user"
                            aria-controls="menu-appbar"
                            aria-haspopup="true"
                            onClick={handleOpenNavMenu}
                            color="inherit"
                        >
                            <MenuIcon />
                        </IconButton>
                        <Menu
                            id="menu-appbar"
                            anchorEl={anchorElNav}
                            anchorOrigin={{
                                vertical: 'bottom',
                                horizontal: 'left',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'left',
                            }}
                            open={Boolean(anchorElNav)}
                            onClose={handleCloseNavMenu}
                            sx={{
                                display: { xs: 'block', md: 'none' }
                            }}
                        >
                            {pages.map((page) => (
                                <MenuItem key={page} onClick={handleCloseNavMenu}>
                                    <Typography textAlign="center">{page}</Typography>
                                </MenuItem>
                            ))}
                        </Menu>
                    </Box>

                    <Box sx={{ flexGrow: 1, display: "flex", alignItems: "center"}}>
                        <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' }, alignItems: "center"}}>
                            <HomePageLogoBtn />
                        </Box>
                        <Box sx={{ mr: 2, display: { xs: 'none', md: 'flex' } , alignItems: "center" }}>
                            <HomePageLogoBtn />
                        </Box>
                    </Box>

                    <Box sx={{ flexGrow: 0, display: "flex", flexDirection: "row", alignItems: "center", justifyContent: 'center'}}>
                        <Box sx={{marginRight: "20px", display: "flex", alignItems: "center", justifyContent: "center"}}>
                            {authentication.isUserAuthenticated ? undefined : <SignInIcon />}
                        </Box>
                        <Box sx={{marginRight: "20px"}}>
                            <ShoppingCartIcon />
                        </Box>
                        <Box>
                            <UserMenu />
                        </Box>
                    </Box>
            </Container>
        </AppBar>
    );
};
export default ResponsiveAppBar;
