export interface HttpResponse <S,E = string | null>{
    data: S,
    error: E,
    loading: boolean
}

export default HttpResponse