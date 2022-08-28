import EmptyHttpResponse from "./EmptyHttpResponse";

export interface HttpResponse <S,E = string | null> extends EmptyHttpResponse<E>{
    data: S,
}

export default HttpResponse