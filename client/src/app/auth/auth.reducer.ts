// auth.reducer.ts
import { createReducer, on } from '@ngrx/store';
import { login, logout } from './auth.actions';

export interface AuthState {
  isLoggedIn: boolean;
  token: string | null;
  userDetails: any | null;
}

export const initialState: AuthState = {
  isLoggedIn: false,
  token: null,
  userDetails: null,
};

export const authReducer = createReducer(
  initialState,
  on(login, (state, { token, userDetails }) => ({
    ...state,
    isLoggedIn: true,
    token,
    userDetails,
  })),
  on(logout, (state) => ({
    ...state,
    isLoggedIn: false,
    token: null,
    userDetails: null,
  }))
);
