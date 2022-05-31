import { useState } from "react"

const useViewModel = ()=>{
    const [chekcBoxStates, setCheckBoxStates] = useState(getInitStates(5))
    return getCheckBoxViewModels(chekcBoxStates, setCheckBoxStates)
}
const getCheckBoxLabels = ()=>([
    "Brand 1",
    "Brand 2",
    "Brand 3",
    "Brand 4",
    "Brand 5",
    "Brand 6",
    "Brand 7",
    "Brand 8",
    "Brand 9",
    "Brand 10",
])
const getCheckBoxViewModels =  (states: boolean[], setStates: React.Dispatch<React.SetStateAction<boolean[]>>)=>{
    const limit = 5
    return getCheckBoxLabels().filter((label, index)=>index <= limit-1).map((label, index)=>({
            label,
            state: states[index],
            setState: (newState: boolean, index: number)=>{
                const newStates = states.map((oldState, oldeStateIndex)=>oldeStateIndex === index ? newState: oldState)
                setStates(newStates)
            }
        }))

}
const getInitStates = (size: number)=>{
    const initStates = []
    for(let i = 0; i < size; i++){
        initStates.push(false)
    }
    return initStates
}
export default useViewModel