import useViewModel from "./vm";

const CategoriesStack = ()=>{
    const viewModel = useViewModel()
    return (
        <div>{viewModel}</div>
    )
}

export default CategoriesStack