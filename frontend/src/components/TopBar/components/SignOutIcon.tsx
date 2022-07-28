import useDestroyAuthentication from "../../../data-sources/authentication/useDestroyAuthentication"

const SignOutIcon = ()=>{
    const destroyAuthentication = useDestroyAuthentication()
    const handleClick = ()=>destroyAuthentication()

    return <div onClick={handleClick}>Sign out</div>
}
export default SignOutIcon