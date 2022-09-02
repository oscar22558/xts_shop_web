import Address from "./Address"

type User = {
    id: number
    username: string
    email: string
    phone: string
    addresses: Address[]
}

export default User