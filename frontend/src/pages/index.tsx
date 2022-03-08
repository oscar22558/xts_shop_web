import styled from "@emotion/styled";
import { Button } from "@mui/material";

const Input = styled('input')({
  display: 'none',
});
const Index = ()=>{
    return (
        <>
                                <label htmlFor="contained-button-file">
                            <input accept="image/*" id="contained-button-file" multiple type="file" onChange={(event)=>{
                                const file = event?.currentTarget?.files?.[0]

                                if(file){
                                    const data = new FormData()
                                    data.append("files", file)
                                    fetch("http://localhost:8080/storage", {
                                        method: "post",
                                        body: data
                                    }).then(result=>{
                                        return result.json()
                                    }).then(data=>{
                                        console.log(data)
                                    }).catch(error=>{
                                        console.error(error)
                                    })
                                }

                            }} />
                            <Button variant="contained" component="span">
                            Upload
                            </Button>
                        </label>
        </>

    )
}
export default Index