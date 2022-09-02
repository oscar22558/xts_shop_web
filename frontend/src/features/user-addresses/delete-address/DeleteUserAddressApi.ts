import ApiRequest from "../../ApiRequest";
import DeleteAddressForm from "../models/DeleteAddressForm";

const DeleteAddressApi = ({addressId}: DeleteAddressForm)=>ApiRequest({
    method: "delete",
    url: "/users/address/"+addressId
})
export default DeleteAddressApi