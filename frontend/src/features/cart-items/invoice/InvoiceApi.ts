import { ApiRequestWithoutToken } from "../../ApiRequest";
import GetInvoiceRequest from "./models/GetInvoiceRequest";

const InvoiceApi = (data: GetInvoiceRequest)=>ApiRequestWithoutToken({
    url: "/users/invoice",
    method: "POST",
    data
})
export default InvoiceApi