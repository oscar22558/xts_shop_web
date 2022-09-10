import ApiRequest from "../ApiRequest";

const ValidateAuthTokenApi = ()=>ApiRequest({
    method: "post",
    url: "/auth/valid",
})

export default ValidateAuthTokenApi