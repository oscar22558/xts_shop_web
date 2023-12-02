import { TypedUseSelectorHook, useDispatch, useSelector } from 'react-redux'
import type { RootState, Dispatch } from './Store'

// Use throughout your redux instead of plain `useDispatch` and `useSelector`
export const useAppDispatch = () => useDispatch<Dispatch>()
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector