import { useState } from "react";
import { useCookies } from "react-cookie";
import CookieType from "./models/CookieType";

type CookieTypeKeys = keyof CookieType & string
type CartCookieType = CookieType["carts"]
const useCartCookie = ()=>{
    const cartCookieKey: CookieTypeKeys = "carts"
    const [cookie, setCookie] = useCookies([cartCookieKey])
    const cartCookie = (cookie.carts as CartCookieType) ?? {}

    const getItemIdList = ()=>Object.keys(cartCookie)
        .map((itemId)=>Number.parseInt(itemId))
    const [itemIdList, setItemIdList] = useState(getItemIdList())
    const setCartCookie = (cartCookie: CartCookieType)=>{
        setCookie(cartCookieKey, cartCookie, {path: "/"})
    }

    return {
        cartCookie,
        setCartCookie,
        itemIdList
    }
}
export default useCartCookie