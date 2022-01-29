import { GetStaticProps, InferGetStaticPropsType } from "next"
import React from "react"
import { useQuery, gql } from "@apollo/client"
const categoriesQuery = gql`
{
    allCategories {
        id
        name
        subCategories{
            id
            name
        }
    }
}
`
export const getStaticProps: GetStaticProps = async (context) => {
    return {
        props: {  }
    }
}
const CategoryItem = (props: any)=>{
    const subCategories = props.data.subCategories
    return (
        <div>
            <div>
                {`${props.data.id}: ${props.data.name}`}
            </div>
            {
                subCategories &&
                <>
                    <div>sub Categories:</div>
                    {props.data.subCategories?.map((subCat: any)=><CategoryItem data={subCat}/>)}
                </>
            }
        </div>
    )
}

const FirstPage = ({}: InferGetStaticPropsType<typeof getStaticProps>)=>{
    // return data.map((categoryItemData: any)=>(<CategoryItem data={categoryItemData} />))
    const result = useQuery(categoriesQuery);
    const { data, error, loading, networkStatus } = result
    console.log(data)
    return (
        <>
            <div>{`result: ${result}`}</div>
            <div>{`loading: ${loading}`}</div>
            <div>{`error: ${error}`}</div>
            <div>{`networkStatus: ${networkStatus}`}</div>
            <div>{`data: ${data}`}</div>
            {/* <CategoryItem data={data} /> */}
            {data?.allCategories?.map((category: any)=><CategoryItem data={category} />)}
        </>
    )
}

export default FirstPage