import Address from "../../../user/models/Address"
import NewOrderItem from "../../models/NewOrderItem"

type CreatePaymentIntentForm = {
    itemQuantities: NewOrderItem[],
    recipientFirstName: string
    recipientLastName: string
    recipientEmail: string
    recipientPhone: string
} & Omit<Address, "id">
export default CreatePaymentIntentForm