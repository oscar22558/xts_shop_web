import storage from 'redux-persist/lib/storage';
import { ReducerKeysType } from './RootReducer';


const migrations = {
    1: (state: any)=>{
        const initialRegistryState = {
            postRegistryResponse: {
                error: {
                    username: "",
                    password: "",
                    email: "",
                    phone: ""
                },
                loading: false
            }
        }
        const registry = state.registry ? state.registry : initialRegistryState

        const validateAuthTokenState = state.authentication.validateAuthToken
        const initialValidateAuthTokenState = {
            error: "",
            loading: false
        }
        const validateAuthToken = validateAuthTokenState ? validateAuthTokenState : initialValidateAuthTokenState
        const authentication = {
            ...state.authentication,
            validateAuthToken
        }

        return {...state, registry, authentication}
    }
}
const whitelist: ReducerKeysType[] = [
    "authentication"
]

const PersistReducerConfig = {
    key: "root",
    storage,
    whitelist,
    version: 1,
    migrate: (state: any)=>Promise.resolve(migrations[1](state))
}
export default PersistReducerConfig