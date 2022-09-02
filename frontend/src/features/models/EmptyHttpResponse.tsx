export interface EmptyHttpResponse <E = string | null>{
    error: E,
    loading: boolean
}

export default EmptyHttpResponse