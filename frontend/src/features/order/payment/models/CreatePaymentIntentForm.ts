import NewOrderItem from "../../models/NewOrderItem"

type CreatePaymentIntentForm = {
    itemQuantities: NewOrderItem[],
    userAddressId: number
    recipientFirstName: string
    recipientLastName: string
    recipientEmail: string
    recipientPhone: string
}
export default CreatePaymentIntentForm