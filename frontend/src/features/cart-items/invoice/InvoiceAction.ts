import { createAction } from "@reduxjs/toolkit"
import InvoiceSlice from "./InvoiceSlice"
import GetInvoiceRequest from "./models/GetInvoiceRequest"

const actions = InvoiceSlice.actions
const GetInvoiceAction = {
    start: actions.getInvoiceStart,
    end: actions.getInvoiceEnd,
    succeed: actions.getInvoiceSucceed,
    fail: actions.getInvoiceFail,
    async: createAction<GetInvoiceRequest>("get-invoice/async")
}
export default GetInvoiceAction