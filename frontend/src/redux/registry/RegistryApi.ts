import ApiRequest from "../ApiRequest";
import RegistryForm from "./RegistrygForm";

export default (data: RegistryForm)=>ApiRequest({
    method: "POST",
    url: "/users",
    data
})