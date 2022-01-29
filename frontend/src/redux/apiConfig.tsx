const protocol = "http"
const domain = "localhost"
const port = "8080"
const routePrefix = "api"
const config = {
    baseURL: `${protocol}://${domain}:${port}/${routePrefix}`,
    method: "get",
    headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
    }
}
export default config