import ApiRequest from "../../ApiRequest";
import CreatePaymentIntentForm from "./models/CreatePaymentIntentForm";

const CreatePaymentIntentApi = (data: CreatePaymentIntentForm)=>ApiRequest({
    url: "/payment-intent",
    method: "POST",
    data
})
export default CreatePaymentIntentApi