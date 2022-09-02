import ApiRequest from "../../ApiRequest";
import GetInvoiceRequest from "./models/GetInvoiceRequest";

const InvoiceApi = (data: GetInvoiceRequest)=>ApiRequest({
    url: "/users/invoice",
    method: "POST",
    data
})
export default InvoiceApi