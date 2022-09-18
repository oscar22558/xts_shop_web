export const protocol = "http"
export const domain = "localhost"
export const port = "8080"
export const routePrefix = "api"
export const host = process.env.NODE_ENV === "development" ? `${protocol}://${domain}:${port}` : ""
export const baseURL = `${host}/${routePrefix}`
const config = {
    baseURL,
    method: "get",
    headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
    }
}
export default config