import storage from 'redux-persist/lib/storage';
import { ReducerKeysType } from './RootReducer';

const whitelist: ReducerKeysType[] = [
    "authentication"
]

const PersistReducerConfig = {
    key: "root",
    storage,
    whitelist
}
export default PersistReducerConfig