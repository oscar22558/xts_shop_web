import StyledTabs from "./StyledTabs";
import StyledTab from "./StyledTab";
import React, {useState} from "react";
import {useAppSelector} from "../../redux/hooks";
import selector from "../../redux/categories/selector"
import useViewModel from "./useViewMode"
import { useNavigate } from "react-router-dom";

const CategoriesTabs = ()=>{
    const { data } = useAppSelector(selector).all
    const [value, setValue] = useState<number|boolean>(false)
    const navigate = useNavigate()
    const viewModel = useViewModel()
    const handleChange = (event: React.SyntheticEvent, newValue: number) => {
        setValue(newValue);
    };
    function a11yProps(index: number) {
        return {
            id: `simple-tab-${index}`,
            'aria-controls': `simple-tabpanel-${index}`,
        };
    }
   return (
       <StyledTabs value={value} onChange={handleChange} aria-label="basic tabs example">
           {data.map((category, index)=>(
                <StyledTab 
                    key={index} 
                    label={category.name} 
                    {...a11yProps(index)}
                    onClick={()=>{
                        viewModel.handleItemClick(category._links.self.href, category._links.items.href)
                        navigate(`/categories/${category.name}`)
                    }}
                />
           ))}
       </StyledTabs>
   )
}

export default CategoriesTabs