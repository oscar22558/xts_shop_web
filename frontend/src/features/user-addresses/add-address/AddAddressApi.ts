import ApiRequest from "../../ApiRequest";
import AddAddressForm from "../models/AddAddressForm";

export const AddAddressesApi = (data: AddAddressForm)=>ApiRequest({
    method: "post",
    url: "/users/address",
    data
})

export default AddAddressesApi