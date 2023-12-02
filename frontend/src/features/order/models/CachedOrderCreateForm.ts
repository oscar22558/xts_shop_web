import Address from "../../user/models/Address"

type CachedOrderCreateForm = {
    firstName: string
    lastName: string
    email: string
    phone: string
} & Omit<Address, "id">
export default CachedOrderCreateForm