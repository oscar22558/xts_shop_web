export const protocol = "http"
export const domain = "localhost"
export const port = "8080"
export const routePrefix = "api"
const config = {
    baseURL: `${protocol}://${domain}:${port}/${routePrefix}`,
    method: "get",
    headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
    }
}
export default config