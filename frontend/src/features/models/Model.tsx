import Link from "./Link";

interface Model {
    _links: {
        self: Link
        all: Link
    }
}
export default Model