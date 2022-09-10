import axiosInstance, { ApiRequestWithoutToken } from "../ApiRequest";

export const getAllBrandsApi = (url: string)=>ApiRequestWithoutToken({
    url,
})

