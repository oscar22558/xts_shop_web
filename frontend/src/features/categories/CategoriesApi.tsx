import axiosInstance from "../ApiRequest";

export const CategoriesApi = {
    getAll: (url: string)=>axiosInstance({
        url
    }),
    get: (url: string)=>axiosInstance({
        url,
    })
}
export default CategoriesApi