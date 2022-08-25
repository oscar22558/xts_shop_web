import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import HttpResponse from "../../models/HttpResponse";
import Invoice from "./models/Invoice";

type State = {
    getInvoiceResponse: HttpResponse<Invoice>
}

const initialState: State = {
    getInvoiceResponse: {
        data: {
            subItemTotal: 0,
            shippingFee: 0,
            total: 0
        },
        error: null,
        loading: false
    }
}

const InvoiceSlice = createSlice({
    name: "invoice",
    initialState,
    reducers: {
        getInvoiceStart: (state: State)=>{
            state.getInvoiceResponse.loading = true
        },
        getInvoiceEnd: (state: State)=>{
            state.getInvoiceResponse.loading = false
        },
        getInvoiceSucceed: (state: State, {payload}: PayloadAction<Invoice>)=>{
            state.getInvoiceResponse.error = null
            state.getInvoiceResponse.data = payload
        },
        getInvoiceFail: (state: State, {payload}: PayloadAction<string>)=>{
            state.getInvoiceResponse.error = payload
        }
    }
})

export const InvoiceReducer = InvoiceSlice.reducer
export default InvoiceSlice