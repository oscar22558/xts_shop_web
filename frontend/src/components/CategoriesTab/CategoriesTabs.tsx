import React from "react";
import { useNavigate } from "react-router-dom";
import {useAppDispatch, useAppSelector} from "../../features/Hooks";

import CategoriesSelector from "../../features/categories/CategoriesSelector"
import CategoriesActions from "../../features/categories/CategoriesAction";

import useViewModel from "./useViewMode"
import StyledTabs from "./StyledTabs";
import StyledTab from "./StyledTab";

const CategoriesTabs = ()=>{
    const navigate = useNavigate()
    const dispatch = useAppDispatch()
    const categoriesState = useAppSelector(CategoriesSelector)
    const viewModel = useViewModel()

    const { data } = categoriesState.getAllCategoriesResponse
    const selectedCategoryTabIndex = categoriesState.selectedCategoryTabIndex !== -1 ? categoriesState.selectedCategoryTabIndex : false

    const handleChange = (event: React.SyntheticEvent, newValue: number) => {
        dispatch(CategoriesActions.setSelectedCategoryTabIndex(newValue))
    }

    const a11yProps = (index: number)=>{
        return {
            id: `simple-tab-${index}`,
            'aria-controls': `simple-tabpanel-${index}`,
        }
    }

    return (
       <StyledTabs value={selectedCategoryTabIndex} onChange={handleChange} aria-label="basic tabs example">
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