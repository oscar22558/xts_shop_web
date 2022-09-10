import { useCookies } from "react-cookie";
import CartCookieType from "./models/CartCookieType";
import CookieType from "./models/CookieType";

type CookieTypeKeys = keyof CookieType & string
const useCartCookie = ()=>{
    const cartCookieKey: CookieTypeKeys = "carts"
    const [cookie, setCookie] = useCookies([cartCookieKey])
    const cartCookie = (cookie.carts as CartCookieType) ?? {}
    const setCartCookie = (cartCookie: CartCookieType)=>{
        setCookie(cartCookieKey, cartCookie, {path: "/"})
    }

    return {
        cartCookie,
        setCartCookie
    }
}
export default useCartCookie