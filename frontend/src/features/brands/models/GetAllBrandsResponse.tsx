import Brand from "./Brand"

type GetAllBrandsResponse = {
    _embedded: {
        brandRepresentationModelList: Brand[]
    }
}

export default GetAllBrandsResponse