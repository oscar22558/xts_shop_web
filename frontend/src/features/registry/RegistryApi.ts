import ApiRequest, { ApiRequestWithoutToken } from "../ApiRequest";
import RegistryForm from "./models/RegistrygForm";

export default (data: RegistryForm)=>ApiRequestWithoutToken({
    method: "POST",
    url: "/users",
    data
})