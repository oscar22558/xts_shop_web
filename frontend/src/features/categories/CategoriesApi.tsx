import { ApiRequestWithoutToken } from "../ApiRequest";

export const CategoriesApi = {
    getAll: ()=>ApiRequestWithoutToken({
        url: "/categories"
    }),
    get: (categoryId: number)=>ApiRequestWithoutToken({
        url: `/categories/${categoryId}`,
    })
}
export default CategoriesApi