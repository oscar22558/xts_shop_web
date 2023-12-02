export const toUpperCaseAt = (string: string, index: number)=>{
    if(string.length <= 0) return string
    if(index > string.length - 1 || index < 0) return string
    const char = string.charAt(index).toUpperCase()
    const firstSubstirng = string.substring(0, index)
    const secondSubstirng = string.substring(index + 1, string.length+1)
    return `${firstSubstirng}${char}${secondSubstirng}`
}