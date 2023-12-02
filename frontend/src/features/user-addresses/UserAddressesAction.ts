import { createAction } from "@reduxjs/toolkit"
import AddAddressForm from "./models/AddAddressForm"
import DeleteAddressForm from "./models/DeleteAddressForm"
import { UserAddressesSlice } from "./UserAddressesReducer"

const sliceActions = UserAddressesSlice.actions
const UserAddressAction = {
    addAddress: {
        start: sliceActions.addAddressStart,
        end: sliceActions.addAddressEnd,
        succeed: sliceActions.addAddressSucceed,
        fail: sliceActions.addAddressFail,
        async: createAction<AddAddressForm>("add-user-address/async"),
        clearError: sliceActions.clearAddAddressError
    },
    deleteAddress: {
        start: sliceActions.deleteAddressStart,
        end: sliceActions.deleteAddressEnd,
        succeed: sliceActions.deleteAddressSucceed,
        fail: sliceActions.deleteAddressFail,
        async: createAction<DeleteAddressForm>("delete-user-address/async"),
        clearError: sliceActions.clearDeleteAddressError
    }
}
export default UserAddressAction