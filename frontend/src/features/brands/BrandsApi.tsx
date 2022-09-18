import axiosInstance, { ApiRequestWithoutToken } from "../ApiRequest";

export const getAllBrandsApi = ()=>ApiRequestWithoutToken({
    url: "/brands",
})

