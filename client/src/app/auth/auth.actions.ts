// auth.actions.ts
import { createAction, props } from '@ngrx/store';

export const login = createAction(
  '[Auth] Login',
  props<{ token: string; userDetails: any }>()
);

export const logout = createAction('[Auth] Logout');
