const AppRouteList = {
    home: "/",
    cart: "/cart",
    signIn: "/sign-in",
    orderShippingAddresses: "/shipping-address",
    itemsInCaregory: "/categories",
    orders: "/orders",
    order: {route: "/order/:orderId", params: [":orderId"]},
    settings: {
        index: "/settings",
        account: "",
        addresses: "addresses"
    },
    payment: "/payment",
    item: {route: "/item/:itemId", params: [":itemId"]}
}
export default AppRouteList