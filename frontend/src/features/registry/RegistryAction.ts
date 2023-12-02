import { createAction } from "@reduxjs/toolkit"
import RegistryForm from "./models/RegistrygForm"
import RegistrySlice from "./RegistrySlice"

const actions = RegistrySlice.actions
export default {
    start: actions.postRegistryStart,
    end: actions.postRegistryEnd,
    succeed: actions.postRegistrySucceed,
    fail: actions.postRegistryFail,
    async: createAction<RegistryForm>("post-registry/async"),
    clearError: actions.clearRegistryError
}