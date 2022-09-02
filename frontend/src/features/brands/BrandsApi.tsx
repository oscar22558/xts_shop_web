import axiosInstance from "../ApiRequest";

export const getAllBrandsApi = (url: string)=>axiosInstance({
    url,
})

