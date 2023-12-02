import { createAction } from "@reduxjs/toolkit"
import UpdatePasswordForm from "./models/UpdatePasswordForm"
import User from "./models/User"
import { UsersSlice } from "./UserReducer"

const actions = UsersSlice.actions
export const GetUserAction = {
    start: actions.getUserStart,
    end: actions.getUserEnd,
    succeed: actions.getUserSucceed,
    fail: actions.getUserFail,
    async: createAction("get-user/async")
}

export const UpdateUserAction = {
    start: actions.updateUserStart,
    end: actions.updateUserEnd,
    succeed: actions.updateUserSucceed,
    fail: actions.updateUserFail,
    async: createAction<User>("update-user/async"),
    clearError: actions.clearUpdateUserError
}

export const UpdatePasswordAction = {
    start: actions.updatePasswordStart,
    end: actions.updatePasswordEnd,
    succeed: actions.updatePasswordSucceed,
    fail: actions.updatePasswordFail,
    async: createAction<UpdatePasswordForm>("update-password/async"),
    clearError: actions.clearUpdatePasswordError
}
export default GetUserAction