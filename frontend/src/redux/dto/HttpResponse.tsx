export interface HttpResponse <S>{
    data: S,
    error: string | null,
    loading: boolean
}

export default HttpResponse