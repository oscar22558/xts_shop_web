import axiosInstance, { ApiRequestWithoutToken } from "../ApiRequest";

export const CategoriesApi = {
    getAll: (url: string)=>ApiRequestWithoutToken({
        url
    }),
    get: (url: string)=>ApiRequestWithoutToken({
        url,
    })
}
export default CategoriesApi