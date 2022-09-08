import ApiRequest from "../ApiRequest";

const ValidAuthTokenApi = ()=>ApiRequest({
    method: "post",
    url: "/auth/valid",
})

export default ValidAuthTokenApi