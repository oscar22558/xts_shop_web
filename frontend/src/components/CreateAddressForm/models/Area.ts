enum Area {
    HongKong = "Hong Kong",
    Kowloon = "Kowloon",
    NT = "N.T"
}
export type DistrictIndex = keyof typeof Area
export default Area