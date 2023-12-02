import useCart from "../../../data-sources/cart/useCart"
import AppRouteList from "../../../routes/AppRouteList"
import { Badge, createTheme, IconButton, Link } from "@mui/material"
import MaterialCartIcon from '@mui/icons-material/ShoppingCart';
import { ThemeProvider } from "@emotion/react";

const theme = createTheme({
    palette: {
        primary: {
            light: '#fff',
            main: '#fff',
            dark: '#fff',
            contrastText: '#000',
        }
    },
});

const ShoppingCartIcon = ()=>{
    const {itemTotalCountInCart: itemCountInCart} = useCart()
    return <>
        <Link href={AppRouteList.cart} color="#fff" underline="none">
            <ThemeProvider theme={theme} >
                <IconButton color="primary">
                    <Badge badgeContent={itemCountInCart()} color="secondary">
                        <MaterialCartIcon />
                    </Badge>
                </IconButton>
            </ThemeProvider>
        </Link>
    </>
}
export default ShoppingCartIcon