import ApiRequest from "../ApiRequest";
import UpdatePasswordForm from "./models/UpdatePasswordForm";
import User from "./models/User";

export const GetUserApi = ()=>ApiRequest({
    method: "get",
    url: "/auth/user"
})

export const UpdateUserApi = (updatedUser: User)=>ApiRequest({
    method: "put",
    url: "/users",
    data: updatedUser
})

export const UpdatePasswordApi = (updatePassworForm: UpdatePasswordForm)=>ApiRequest({
    method: "patch",
    url: "/users/password",
    data: updatePassworForm
})
export default GetUserApi