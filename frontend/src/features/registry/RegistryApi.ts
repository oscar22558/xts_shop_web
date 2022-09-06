import ApiRequest from "../ApiRequest";
import RegistryForm from "./models/RegistrygForm";

export default (data: RegistryForm)=>ApiRequest({
    method: "POST",
    url: "/users",
    data
})